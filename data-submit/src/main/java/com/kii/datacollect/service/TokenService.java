package com.kii.datacollect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

@Repository
public class TokenService {

	private static final String TOKEN_STORE = "TOKEN_STORE";


	@Qualifier("stringRedisTemplate")
	@Autowired
	private RedisTemplate template;



	public String queryNameByToken(String token){

		String hashToken = getHashedToken(token);

		return (String) template.boundHashOps(TOKEN_STORE).get(hashToken);
	}



	public void bindToken(String token,String name){

		String hashToken=getHashedToken(token);

		template.boundHashOps(TOKEN_STORE).put(hashToken,name);
	}

	private String getHashedToken(String token) {
		byte[] bytes=(token+"_dataCollect").getBytes();

		return DigestUtils.md5DigestAsHex(bytes);
	}

}
