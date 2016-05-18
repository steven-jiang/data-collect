package com.kii.datacollect.service;

public class JsonException extends RuntimeException {

	public JsonException(Exception e){
		super(e.getMessage(),e);
	}
}
