package com.ipivot.matka.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ControllerUtils {

	public static <T> T convertJsonToObject(String jsonData,Class<T> clazz) throws Exception{
		return new  ObjectMapper().readValue(jsonData,clazz);
	}
}
