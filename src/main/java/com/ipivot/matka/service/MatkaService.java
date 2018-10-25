package com.ipivot.matka.service;

import java.util.List;
import java.util.Set;

import com.ipivot.matka.model.PurchaseOrder;
import com.ipivot.matka.model.SearchRequest;
import com.ipivot.matka.model.SearchResponse;
import com.ipivot.matka.model.VacationPackage;
import com.ipivot.matka.model.VacationPackageDetails;
import com.ipivot.matka.model.User;

public interface MatkaService {

	// package api
	SearchResponse getVacationPackagesByCity(SearchRequest request);
	VacationPackageDetails getVacationPackageDetails(String packageId);
	List<VacationPackage> searchPopularVacationPackages(String origin);
	
	VacationPackage purchaseVacationPackage(PurchaseOrder po);
	void deleteVacationPackageById(String packageId);
	List<VacationPackage> getCustomVacationPackagesByUser(User user);
	void saveVacationPackage(VacationPackage vacationPackage);

	// user api
	User addUser(User user) throws Exception;
	User getUserById(String userId);
	List<User> getAllUsers();
	Set<String> getAllUserIds();
	User updateUser(User user);
	
}
