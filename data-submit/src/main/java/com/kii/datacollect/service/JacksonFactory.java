package com.kii.datacollect.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JacksonFactory {


	final ObjectMapper compactObjectMapper;


	public JacksonFactory() {

		compactObjectMapper=createCompactMapper();
	}

	@Bean(name="compactObjectMapper")
	public ObjectMapper getCompactObjectMapper(){
		return compactObjectMapper;
	}



	private static ObjectMapper createCompactMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		result.configure(SerializationFeature.INDENT_OUTPUT, false);
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		return result;
	}
}
