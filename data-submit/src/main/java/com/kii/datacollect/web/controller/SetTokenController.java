package com.kii.datacollect.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kii.datacollect.service.JsonException;
import com.kii.datacollect.service.JsonMapper;
import com.kii.datacollect.service.TokenService;

@Component
public class SetTokenController implements AbstractController {


	@Autowired
	private TokenService tokenService;

	@Autowired
	private JsonMapper mapper;

	@Override
	public void doPost(String input, String appToken) {


		try {
			Map<String,Object> params = mapper.readValue(input,Map.class);
			String userName= (String) params.get("userName");
			String token=(String)params.get("token");

			tokenService.bindToken(token,userName);

		} catch (JsonException e) {
			Map<String,Object> err=new HashMap<>();
			err.put("errorCode","INVALID_FORMAT");
			err.put("errorMsg",e.getMessage());

			String errMsg="{}";
			errMsg=mapper.writeValueAsString(err);

			throw new IllegalArgumentException(errMsg);
		}


	}
}
