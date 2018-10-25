package com.ipivot.matka.service;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ipivot.matka.mock.bind.packages.PackageDetails;
import com.ipivot.matka.mock.bind.packages.Packages;
import com.ipivot.matka.mock.bind.users.ObjectFactory;
import com.ipivot.matka.mock.bind.users.Users;
import com.ipivot.matka.model.Flight;
import com.ipivot.matka.model.PurchaseOrder;
import com.ipivot.matka.model.Resort;
import com.ipivot.matka.model.SearchRequest;
import com.ipivot.matka.model.SearchResponse;
import com.ipivot.matka.model.User;
import com.ipivot.matka.model.VacationPackage;
import com.ipivot.matka.model.VacationPackageDetails;

@Service("matkaService")
public class MatkaServiceImpl implements MatkaService {

	private static Logger logger = LoggerFactory.getLogger(MatkaServiceImpl.class);
	private static List<VacationPackageDetails> detailedVacationPackages;
	private static Map<String, User> registeredUsersMap;

	static {
		detailedVacationPackages = loadVacationPackageDetails();
		registeredUsersMap = loadRegisteredUsers();
	}

	/**
	 * search service implementation
	 */
	public SearchResponse getVacationPackagesByCity(SearchRequest request) {
		SearchResponse response = new SearchResponse();

		// get total # of rec for search destination
		int totalCount = 0;
		for (VacationPackageDetails vacationPackage : detailedVacationPackages) {
			if (request.getName().equalsIgnoreCase(vacationPackage.getVacationPackage().getResort().getCity())) {
				totalCount++;
			}
		}
		logger.info("total # of recs (totalCount) for city, " + request.getName() + " : " + totalCount);

		int perPageCount = request.getPerPageCount();
		int pageRequested = request.getPageNumber();

		int numOfPages = 0;
		if ((totalCount % perPageCount) == 0) {
			numOfPages = totalCount / perPageCount;
		} else {
			if (totalCount < perPageCount) {
				numOfPages = 1;
			} else {
				numOfPages = (totalCount / perPageCount) + 1;
			}
		}
		logger.info("total # of pages (numOfPages) for city, " + request.getName() + " : " + numOfPages);

		int startRec = 1;
		if (pageRequested != 1) {
			startRec = (perPageCount * (pageRequested - 1)) + 1;
		}
		logger.info("startRec: " + startRec);

		int endRec = startRec + (perPageCount - 1);
		logger.info("endRec: " + endRec);

		int returnCount = 0;
		int i = 1, j = 1;
		List<VacationPackage> packages = new ArrayList<VacationPackage>();
		for (VacationPackageDetails vacationPackage : detailedVacationPackages) {
			if (request.getName().equalsIgnoreCase(vacationPackage.getVacationPackage().getResort().getCity())) {
				long timeDiff = request.getFromDate().getTime()
						- vacationPackage.getVacationPackage().getStartDate().getTime();
				int daysDiff = (int) (timeDiff / (24 * 60 * 60 * 1000));
				if ((-30 < daysDiff) || (daysDiff < 30)) {
					if ((i == startRec || j <= endRec) && returnCount <= perPageCount) {
						packages.add(vacationPackage.getVacationPackage());
						returnCount++;
					} else {
						i++;
						j++;
						continue;
					}
				}
				i++;
				j++;
			}
		}
		response.setPackages(packages);
		response.setPageNumber(request.getPageNumber());
		response.setPerPageCount(perPageCount);
		response.setNumOfPages(numOfPages);
		response.setTotalCount(totalCount);
		return response;
	}

	/**
	 * package details implementation
	 */
	public VacationPackageDetails getVacationPackageDetails(String vacationPackageId) {
		VacationPackageDetails response = null;
		for (VacationPackageDetails vacationPackage : detailedVacationPackages) {
			if (Integer.parseInt(vacationPackageId) == vacationPackage.getVacationPackage().getId()) {
				response = vacationPackage;
			}
		}
		return response;
	}

	/**
	 * search popular packages
	 */
	public List<VacationPackage> searchPopularVacationPackages(String originCity) {
		List<VacationPackage> response = new ArrayList<VacationPackage>();

		if (StringUtils.isNotEmpty(originCity)) {
			// UI sent origin city.. so, look for all packages >4 in this city.
			// if not any, lookup general popular
			for (VacationPackageDetails vacationPackage : detailedVacationPackages) {
				VacationPackage packaje = vacationPackage.getVacationPackage();
				String city = packaje.getResort().getCity();
				if (originCity.equalsIgnoreCase(city)) {
					if (Integer.parseInt(packaje.getRating()) >= 4
							|| Double.parseDouble(packaje.getResort().getRating()) >= 4.0) {
						logger.info(packaje.getResort().getName(),
								"{} is returned as popular package within the chosen city: ", city);
						response.add(packaje);
						if (response.size() == 3) {
							break;
						}
					}
				}
			}
		}
		if (response.size() < 3) {
			// will come here after we added whatever popular packages we wanted
			// to add for the chosen city. still, not enough.. so, add remaining
			// generic popular
			for (VacationPackageDetails vacationPackage : detailedVacationPackages) {
				VacationPackage packaje = vacationPackage.getVacationPackage();
				if (Integer.parseInt(packaje.getRating()) >= 4
						|| Double.parseDouble(packaje.getResort().getRating()) >= 4.0
								&& !originCity.equalsIgnoreCase(packaje.getResort().getCity())) {
					logger.info(packaje.getResort().getName(),
							"{} is returned as popular package (not within chosen city): ");
					response.add(packaje);
					if (response.size() == 3) {
						break;
					}
				}
			}
		}
		return response;
	}

	/**
	 * add user
	 */
	public User addUser(User user) throws Exception {
		// validate userId
		if (registeredUsersMap.containsKey(user.getUserId())) {
			logger.error("Invalid user; userId already exists; cannot add this user to db");
			throw new Exception();
		}
		// add incoming user to the list of reg users
		registeredUsersMap.put(user.getUserId(), user);
		return user;
	}

	/**
	 * update user
	 */
	public User updateUser(User user) {
		// validate userId
		if (registeredUsersMap.containsKey(user.getUserId())) {
			// update map with incoming info
			registeredUsersMap.put(user.getUserId(), user);
		}
		// add incoming user to the list of reg users
		registeredUsersMap.put(user.getUserId(), user);
		return user;
	}

	/**
	 * get user info based on userId
	 */
	public User getUserById(String userId) {
		if (registeredUsersMap.containsKey(userId)) {
			return registeredUsersMap.get(userId);
		}
		return null;
	}

	/**
	 * return all users in db
	 */
	public List<User> getAllUsers() {
		return registeredUsersMap != null ? new ArrayList<User>(registeredUsersMap.values()) : null;
	}

	/**
	 * return all userIds in db
	 */
	public Set<String> getAllUserIds() {
		return registeredUsersMap != null ? registeredUsersMap.keySet() : null;
	}

	public VacationPackage purchaseVacationPackage(PurchaseOrder po) {
		// TODO - explore transaction attributes
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteVacationPackageById(String tripId) {
		// TODO Auto-generated method stub

	}

	public List<VacationPackage> getCustomVacationPackagesByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveVacationPackage(VacationPackage vacationPackage) {
		// TODO Auto-generated method stub

	}

	private static List<VacationPackageDetails> loadVacationPackageDetails() {
		List<VacationPackageDetails> packageDetailsList = new ArrayList<VacationPackageDetails>();
		List<com.ipivot.matka.mock.bind.packages.PackageDetails> availableDetailedPackagesListFromXml = loadVacationPackageDetailsFromXml();

		for (int i = 0; i < availableDetailedPackagesListFromXml.size(); i++) {
			VacationPackageDetails packageDetails = new VacationPackageDetails();
			PackageDetails availableDetailedPackageFromXml = availableDetailedPackagesListFromXml.get(i);
			packageDetails.setLongDesc(availableDetailedPackageFromXml.getLongDesc());

			com.ipivot.matka.mock.bind.packages.Package availablePackageFromXml = availableDetailedPackageFromXml
					.getPackage();
			VacationPackage vacationPackage = new VacationPackage();
			vacationPackage.setBriefDesc(availablePackageFromXml.getBriefDesc());
			vacationPackage.setDuration(availablePackageFromXml.getDuration());
			vacationPackage.setEndDate(availablePackageFromXml.getEndDate().toGregorianCalendar().getTime());

			Flight flight = new Flight();
			flight.setDestinationArrivalTime(availablePackageFromXml.getFlight().getDestinationArrivalTime());
			flight.setDestinationDepartureTime(availablePackageFromXml.getFlight().getDestinationDepartureTime());
			flight.setName(availablePackageFromXml.getFlight().getName());
			flight.setSourceArrivalTime(availablePackageFromXml.getFlight().getSourceArrivalTime());
			flight.setSourceDepartureTime(availablePackageFromXml.getFlight().getSourceDepartureTime());
			flight.setType(availablePackageFromXml.getFlight().getType());
			vacationPackage.setFlight(flight);

			vacationPackage.setId(availablePackageFromXml.getId());
			vacationPackage.setNoOfDays("" + availablePackageFromXml.getNoOfDays());
			vacationPackage.setNotes(availablePackageFromXml.getNotes());
			vacationPackage.setPrice(availablePackageFromXml.getPrice() != null
					? new BigDecimal(availablePackageFromXml.getPrice()) : null);
			vacationPackage.setRating("" + availablePackageFromXml.getRating());

			Resort resort = new Resort();
			resort.setCity(availablePackageFromXml.getResort().getCity());
			resort.setCountry(availablePackageFromXml.getResort().getCountry());
			resort.setFeatures(availablePackageFromXml.getResort().getFeatures());
			// resort.setImages(availablePackageFromXml.getResort().getImages());
			resort.setName(availablePackageFromXml.getResort().getName());
			resort.setRating(availablePackageFromXml.getResort().getRating());
			resort.setState(availablePackageFromXml.getResort().getState());
			resort.setStreet(availablePackageFromXml.getResort().getStreet());
			resort.setZipCode("" + availablePackageFromXml.getResort().getZipCode());
			vacationPackage.setResort(resort);

			vacationPackage.setStartDate(availablePackageFromXml.getStartDate().toGregorianCalendar().getTime());
			vacationPackage.setTitle(availablePackageFromXml.getTitle());
			vacationPackage.setTotalCost(availablePackageFromXml.getTotalCost() != 0
					? new BigDecimal(availablePackageFromXml.getTotalCost()) : null);
			vacationPackage.setValidity(availablePackageFromXml.getValidity() != null
					? ("Y".equalsIgnoreCase(availablePackageFromXml.getValidity()) ? true : false) : false);

			packageDetails.setVacationPackage(vacationPackage);
			availableDetailedPackageFromXml.getPackage();
			packageDetailsList.add(packageDetails);
		}
		return packageDetailsList;
	}

	private static List<com.ipivot.matka.mock.bind.packages.PackageDetails> loadVacationPackageDetailsFromXml() {
		// get users from xml and create java objects = unmarshall
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(com.ipivot.matka.mock.bind.packages.ObjectFactory.class)
					.createUnmarshaller();

			URL url = MatkaServiceImpl.class.getClassLoader().getResource("com/ipivot/matka/mock/data/Packages.xml");
			Packages allDetailedPackagesFromXml = (Packages) unmarshaller.unmarshal(url);
			List<com.ipivot.matka.mock.bind.packages.PackageDetails> detailedPackages = allDetailedPackagesFromXml
					.getPackageDetails();
			return detailedPackages;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * returns service user objects from bind objects
	 * 
	 * @return
	 */
	private static Map<String, User> loadRegisteredUsers() {
		Map<String, User> users = new HashMap<String, User>();
		List<com.ipivot.matka.mock.bind.users.User> registeredUsers = loadRegisteredUsersFromXml();

		for (int i = 0; i < registeredUsers.size(); i++) {
			User user = new User();
			com.ipivot.matka.mock.bind.users.User registeredUser = registeredUsers.get(i);
			String userId = registeredUser.getUserId();
			user.setUserId(userId);
			user.setFirstName(registeredUser.getFirstName());
			user.setLastName(registeredUser.getLastName());
			user.setGender(registeredUser.getGender());
			user.setAge(registeredUser.getAge());
			user.setCity(registeredUser.getCity());
			user.setCountry(registeredUser.getCountry());
			user.setCurrency(registeredUser.getCurrency());
			user.setPrimaryEmail(registeredUser.getPrimaryEmail());
			user.setSecondaryEmail(registeredUser.getSecondaryEmail());
			user.setMobilePhone(registeredUser.getMobilePhone());
			user.setHomePhone(registeredUser.getHomePhone());
			users.put(userId, user);
		}
		return users;
	}

	/**
	 * unmarshall xml to java
	 * 
	 * @return
	 */
	private static List<com.ipivot.matka.mock.bind.users.User> loadRegisteredUsersFromXml() {
		// get users from xml and create java objects = unmarshall
		try {
			Unmarshaller unmarshaller = JAXBContext.newInstance(ObjectFactory.class).createUnmarshaller();

			URL url = MatkaServiceImpl.class.getClassLoader().getResource("com/ipivot/matka/mock/data/Users.xml");
			Users usersXml = (Users) unmarshaller.unmarshal(url);
			List<com.ipivot.matka.mock.bind.users.User> regUsers = usersXml.getUser();
			return regUsers;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// private static List<VacationPackageDetails>
	// populateDummyVacationPackageDetails() {
	// List<VacationPackageDetails> packageDetailsList = new
	// ArrayList<VacationPackageDetails>();
	// for (int i = 1; i <= 10; i++) {
	// packageDetailsList.add(setVacationPackageDetailsInfo(new
	// VacationPackageDetails(), i));
	// }
	// return packageDetailsList;
	// }
	//
	// private static VacationPackageDetails
	// setVacationPackageDetailsInfo(VacationPackageDetails d, int i) {
	// d.setVacationPackage(setVacationPackageInfo(new VacationPackage(), i));
	// d.setLongDesc("long desc" + i);
	// return d;
	// }
	//
	// private static VacationPackage setVacationPackageInfo(VacationPackage v,
	// int i) {
	// v.setResort(setResortInfo(new Resort(), i));
	// v.setFlight(setFlightInfo(new Flight()));
	// if (i == 6) {
	// v.setRating("3");
	// } else {
	// v.setRating("4");
	// }
	// v.setId(counter.incrementAndGet());
	// v.setTotalCost(new BigDecimal(500.00));
	// v.setNoOfDays("7");
	// v.setStartDate(MatkaServiceUtil.getDateFromString("20160801",
	// "yyyyMMdd"));
	// v.setEndDate(MatkaServiceUtil.getDateFromString("20160807", "yyyyMMdd"));
	// return v;
	// }
	//
	// private static Flight setFlightInfo(Flight f) {
	// f.setName("American Airlines");
	// f.setType("Economy");
	// f.setSourceDepartureTime("12:00PM");
	// f.setDestinationArrivalTime("05:00PM");
	// f.setDestinationDepartureTime("03:30PM");
	// f.setSourceArrivalTime("08:30PM");
	// return f;
	// }
	//
	// private static Resort setResortInfo(Resort r, int i) {
	// r.setName("Westin" + i);
	// if (i == 6) {
	// r.setRating("3.0");
	// }
	// r.setStreet("123 st");
	// if (i <= 5) {
	// r.setCity("NYC");
	// } else {
	// r.setCity("ABC");
	// }
	// r.setState("NY");
	// r.setCountry("USA");
	// r.setZipCode("12345");
	// r.setFeatures("any features" + i);
	// r.setImages(new ArrayList<String>());
	// return r;
	// }

}
