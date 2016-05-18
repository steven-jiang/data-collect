package com.kii.datacollect.web.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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



	private Pattern linePat=Pattern.compile("\\}\\s*(,|\r\n)\\s*\\{",Pattern.MULTILINE);

	@Override
	public void  doPost(String input,String token){

		String name=tokenService.queryNameByToken(token);

		if(linePat.matcher(input).find()){

			String[] jsonArray=linePat.split(input);

			for(int i=0;i<jsonArray.length;i++){

				if(i==0){
					jsonArray[i]=jsonArray[i]+"}";
				}else if(i==jsonArray.length-1){
					jsonArray[i]="{"+jsonArray[i];
				}else{
					jsonArray[i]="{"+jsonArray[i]+"}";
				}
			}

			List<DataEntity> entitys=new ArrayList<>();
			for(String json:jsonArray){
				entitys.add(generData(json));
			}

			dataService.storeData(name,entitys);

		}else{

			DataEntity entity=generData(input);

			dataService.storeData(name,entity);

		}
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
