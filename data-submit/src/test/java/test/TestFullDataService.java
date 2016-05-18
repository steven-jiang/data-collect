package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

import com.kii.datacollect.web.controller.DataSubmitController;
import com.kii.datacollect.web.controller.SetTokenController;

public class TestFullDataService extends TestTemplate {


	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private DataSubmitController controller;

	@Autowired
	private SetTokenController  tokenController;

	@Autowired
	private ResourceLoader  loader;

	private  String  token="MOCK";


	@Before
	public void before() throws JsonProcessingException {

		Map<String,String> params=new HashMap<>();
		params.put("userName","MOCK_USER");
		params.put("token",token);
		String json=mapper.writeValueAsString(params);

		tokenController.doPost(json,token);
	}

	@Test
	public void testJson() throws IOException {


		String json= StreamUtils.copyToString(loader.getResource("classpath:singleLine.json").getInputStream(), Charsets.UTF_8);


		controller.doPost(json,token);

	}

	@Test
	public void testSplitJson() throws Exception {


		String json= StreamUtils.copyToString(loader.getResource("classpath:demo.json").getInputStream(), Charsets.UTF_8);

		controller.doPost(json,token);

	}
}
