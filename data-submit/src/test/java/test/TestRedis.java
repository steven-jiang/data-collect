package test;

import static junit.framework.TestCase.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.kii.datacollect.service.TokenService;

public class TestRedis extends TestTemplate{

	@Autowired
	private RedisTemplate  template;


	@Autowired
	private TokenService  tokenService;


	@Test
	public void testToken(){

		String token="abcdefgh";
//
		tokenService.bindToken(token,"demo");


		String userName=tokenService.queryNameByToken(token);

		assertEquals(userName,"demo");

	}

	@Test
	public void testHash(){

		BoundHashOperations  oper= template.boundHashOps("demo");

		oper.put("a","123");

		assertEquals("123",oper.get("a"));

	}
}
