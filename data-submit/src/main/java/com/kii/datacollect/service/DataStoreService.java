package com.kii.datacollect.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.kii.datacollect.store.DataEntity;

@Repository
public class DataStoreService {

	@Autowired
	private RedisTemplate  template;

	@Autowired
	private ObjectMapper mapper;

	private String getUUID(String key){

		return key+"_"+template.boundHashOps("INCRE_UUID").increment(key,1);
	}


	public void storeData(String  name,DataEntity  entity){

		if(StringUtils.isEmpty(entity.getId())){
			entity.setId(getUUID(name));
		}

		String json= null;
		try {
			json = mapper.writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		template.boundListOps(name+"_LIST").rightPush(json);

	}

	public void storeData(String name, List<DataEntity> entitys){

		List<String> list=new ArrayList<>();

		entitys.forEach((e)->{
			if(StringUtils.isEmpty(e.getId())) {
				e.setId(getUUID(name));
			}
			try {
				list.add(mapper.writeValueAsString(e));
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
		});

		template.boundListOps(name+"_LIST").rightPushAll(list);
	}

}
