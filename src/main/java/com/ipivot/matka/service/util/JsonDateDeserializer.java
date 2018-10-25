package com.ipivot.matka.service.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException, JsonProcessingException {
		String date = jsonParser.getText();
		try {
			return DateUtils.parseDate(date, "MM/dd/yyyy");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
}
