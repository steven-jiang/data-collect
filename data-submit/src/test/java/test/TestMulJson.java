package test;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.eq;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;

import com.kii.datacollect.service.DataStoreService;
import com.kii.datacollect.service.TokenService;
import com.kii.datacollect.store.DataEntity;
import com.kii.datacollect.web.controller.DataSubmitController;

@PrepareForTest({DataStoreService.class,TokenService.class})
public class TestMulJson extends TestTemplate {

	@InjectMocks
	private DataSubmitController controller;

	@Mock
	private DataStoreService  service;

	@Mock
	private TokenService  tokenService;

	@Spy
	private ObjectMapper mapper;

	@Autowired
	private ResourceLoader loader;


	@Before
	public void before(){

		MockitoAnnotations.initMocks(this);

	}

	private  String  token="MOCK";

	@Test
	public void testJson() throws IOException {

		String json= StreamUtils.copyToString(loader.getResource("classpath:singleLine.json").getInputStream(), Charsets.UTF_8);

		DataEntity  entity=mapper.readValue(json,DataEntity.class);

		assertEquals(entity.getId(),"001");

		assertEquals(entity.getData().toString(),"{\"本月停车次数\":3,\"sourceType\":\"车辆号牌\",\"actionType\":\"进车位\",\"actionDetail\":\"车位001\"}");
	}

	@Test
	public void testSplitJson() throws Exception {

		Mockito.when(tokenService.queryNameByToken(token)).thenReturn("MOCK_USER");

		Mockito.doNothing().when(service).storeData(eq("MOCK_USER"),Mockito.anyListOf(DataEntity.class));

		String muiJson= StreamUtils.copyToString(loader.getResource("classpath:demo.json").getInputStream(), Charsets.UTF_8);

		controller.doPost(muiJson,token);

	}
}
