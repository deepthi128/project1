package com.ipivot.matka.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipivot.matka.model.SearchRequest;
import com.ipivot.matka.model.SearchResponse;
import com.ipivot.matka.model.User;
import com.ipivot.matka.model.VacationPackage;
import com.ipivot.matka.model.VacationPackageDetails;
import com.ipivot.matka.service.MatkaService;
import com.ipivot.matka.service.util.MatkaServiceUtil;

@RestController
public class MatkaServiceController {

	private static Logger logger = LoggerFactory.getLogger(MatkaServiceController.class);

	@Autowired
	MatkaService matkaService;

	/**
	 * search vacation packages based on a city
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/packages/{city}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchResponse> getVacationPackagesByCity(@PathVariable("city") String city,
			@RequestParam(value = "fromdate", required = false, defaultValue = "20160101") String fromDate,
			@RequestParam(value = "todate", required = false, defaultValue = "20161231") String toDate,
			@RequestParam(value = "pagenum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(value = "origin", required = false, defaultValue = "NYC") String originCity) {
		// @RequestParam Map<String,String> requestParams
		logger.debug("In getVacationPackagesByCity for city: {}", city);

		SearchRequest request = new SearchRequest();
		request.setName(city);
		request.setFromDate(MatkaServiceUtil.getDateFromString(fromDate, "yyyyMMdd"));
		request.setToDate(MatkaServiceUtil.getDateFromString(toDate, "yyyyMMdd"));
		request.setPageNumber(pageNum);
		// TODO - # of rec per page should be config driven
		request.setPerPageCount(10);

		logger.debug("Search request sent: {}", request);

		SearchResponse response = matkaService.getVacationPackagesByCity(request);
		if (response == null) {
			logger.debug("Packages for city, {}", request, " not found");
			return new ResponseEntity<SearchResponse>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SearchResponse>(response, HttpStatus.OK);
	}

	/**
	 * vacation package details
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/packages/details/{id}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<VacationPackageDetails> getVacationPackageDetails(@PathVariable("id") String packageId) {
		logger.debug("Retrieving package details for city, getVacationPackageDetails: {}", packageId);

		VacationPackageDetails response = matkaService.getVacationPackageDetails(packageId);
		if (response == null) {
			logger.debug("Package details for city: {}", packageId, " not found");
			return new ResponseEntity<VacationPackageDetails>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<VacationPackageDetails>(response, HttpStatus.OK);
	}

	/**
	 * search popular packages
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/packages/popular", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VacationPackage>> searchPopularVacationPackages(
			@RequestParam(value = "origin", required = false, defaultValue = "NYC") String originCity) {
		logger.info("Searching for popular packages - in searchPopularVacationPackages..");

		List<VacationPackage> popularPackages = matkaService.searchPopularVacationPackages(originCity);
		if (popularPackages == null) {
			logger.info("Popular packages not found.. should probably not happen.. ");
			return new ResponseEntity<List<VacationPackage>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<VacationPackage>>(popularPackages, HttpStatus.OK);
	}

	/**
	 * add user
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public ResponseEntity<User> addUser(@RequestBody User user) {
		logger.debug("Inserting User Info" + user);
		User addedUser = null;
		try {
			addedUser = matkaService.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<User>(addedUser == null ? user : addedUser, HttpStatus.CREATED);
	}

	/**
	 * get user by userId
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
		logger.debug("Retrieving user by userId, getUserById: {}", userId);

		User user = matkaService.getUserById(userId);
		if (user == null) {
			logger.debug("User with userId: {}", userId, " not found");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * get all users
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/all", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<User>> getAllUsers() {
		logger.info("Retrieving all users, getAllUsers..");
		List<User> users = matkaService.getAllUsers();
		if (users == null) {
			logger.info("No registered users in DB..");
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	/**
	 * get all userIds ONLY
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user/ids", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Set<String>> getAllUserIds() {
		logger.info("Retrieving all userIds, getAllUserIds..");

		Set<String> userIds = matkaService.getAllUserIds();
		if (userIds == null) {
			logger.info("No registered users in DB..");
			return new ResponseEntity<Set<String>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Set<String>>(userIds, HttpStatus.OK);
	}

}
