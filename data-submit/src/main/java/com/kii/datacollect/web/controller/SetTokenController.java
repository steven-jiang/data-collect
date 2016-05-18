package com.kii.datacollect.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.kii.datacollect.service.TokenService;

@Component
public class SetTokenController implements AbstractController {


	@Autowired
	private TokenService tokenService;

	@Autowired
	private ObjectMapper mapper;

	@Override
	public void doPost(String input, String appToken) {


		try {
			Map<String,Object> params = mapper.readValue(input,Map.class);
			String userName= (String) params.get("userName");
			String token=(String)params.get("token");

			tokenService.bindToken(token,userName);

		} catch (IOException e) {
			Map<String,Object> err=new HashMap<>();
			err.put("errorCode","INVALID_FORMAT");
			err.put("errorMsg",e.getMessage());

			String errMsg="{}";

			try{
				errMsg=mapper.writeValueAsString(err);
			}catch(IOException ex){
				e.printStackTrace();
			}
			throw new IllegalArgumentException(errMsg);
		}


	}
}
