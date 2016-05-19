package com.kii.datacollect.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.kii.datacollect.service.DataStoreService;
import com.kii.datacollect.service.JsonException;
import com.kii.datacollect.service.JsonMapper;
import com.kii.datacollect.service.TokenService;
import com.kii.datacollect.store.DataEntity;

@Component
public class DataSubmitController implements AbstractController{


	@Autowired
	private JsonMapper mapper;

	@Autowired
	private DataStoreService  dataService;

	@Autowired
	private TokenService  tokenService;




	@Override
	public void  doPost(String input,String token){

		String name=tokenService.queryNameByToken(token);

		String[] jsonArray=mapper.splitMuiJson(input);

		List<DataEntity> entitys=new ArrayList<>();
		for(String json:jsonArray){
				entitys.add(generData(json));
		}

		dataService.storeData(name,entitys);

		return;

	}

	private DataEntity generData(String input)  {

		try {
			input=StringUtils.replace(input,"\n","");

			return mapper.readValue(input,DataEntity.class);
		} catch (JsonException e) {

			Map<String,Object> map=new HashMap<>();
			map.put("errorCode","INVALID_FORMAT");
			map.put("errorMsg",e.getMessage());

			String jsonErr= "{}";
			jsonErr = mapper.writeValueAsString(map);


			throw new IllegalArgumentException(jsonErr);
		}

	}




}
