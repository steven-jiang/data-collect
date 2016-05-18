package com.kii.datacollect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.kii.datacollect.store.DataEntity;

@Repository
public class DataStoreService {

	private static final String TABLE_NAME = "DATA_LIST";
	@Qualifier("dataEntityRedisTemplate")
	@Autowired
	private RedisTemplate<String,DataEntity>  template;


	private String getUUID(String key){

		return key+"_"+template.boundHashOps("INCRE_UUID").increment(key,1);
	}


	public void storeData(String  name,DataEntity  entity){

		if(StringUtils.isEmpty(entity.getId())){
			entity.setId(getUUID(name));
		}
		entity.setFrom(name);


		template.boundListOps(TABLE_NAME).rightPush(entity);

	}

	public void storeData(String name, List<DataEntity> entitys){


		entitys.forEach((e)->{
			if(StringUtils.isEmpty(e.getId())) {
				e.setId(getUUID(name));
				e.setFrom(name);
			}
		});

		template.boundListOps(TABLE_NAME).rightPushAll(entitys.toArray(new DataEntity[0]));
	}

}
