package com.kii.datacollect.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JsonMapper {


	final ObjectMapper mapper;

	public JsonMapper(){
		this.mapper=createDefaultMapper();
	}
	private static ObjectMapper createDefaultMapper() {
		final ObjectMapper result = new ObjectMapper();
		result.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		result.configure(SerializationFeature.INDENT_OUTPUT, true);
		result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

		return result;
	}


	public <T>  T readValue(String input, Class<T> cls) {

		try {
			return mapper.readValue(input,cls);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}
	}

	public String writeValueAsString(Map<String, Object> map) {

		try {
			return mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new JsonException(e);
		}
	}
}
