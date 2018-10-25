package com.ipivot.matka.client;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class MatkaServiceTestClient {

	private static Logger logger = LoggerFactory.getLogger(MatkaServiceTestClient.class);
	public static final String REST_SERVICE_URI = "http://localhost:8080/matka-services";

	@SuppressWarnings("unchecked")
	private static void getVacationPackagesByCity() {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<String, Object>> vacationPackagesMap = restTemplate.getForObject(REST_SERVICE_URI + "/packages/nyc",
				List.class);

		if (vacationPackagesMap != null) {
			for (LinkedHashMap<String, Object> map : vacationPackagesMap) {
				logger.info("User : id=" + map.get("id") + ", Name=" + map.get("name") + ", Age="
						+ map.get("age") + ", Salary=" + map.get("salary"));
				;
			}
		} else {
			logger.info("No user exist----------");
		}
	}

	public static void main(String args[]) {
		getVacationPackagesByCity();
	}
}