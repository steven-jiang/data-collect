package test;

import static junit.framework.TestCase.assertEquals;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StreamUtils;

import com.google.common.base.Charsets;

import com.kii.datacollect.service.DataStoreSparkService;
import com.kii.datacollect.service.JsonMapper;
import com.kii.datacollect.store.SparkDataEntity;

public class TestDataStore extends TestTemplateWithoutMock {



	@Autowired
	private DataStoreSparkService service;


	@Autowired
	private ResourceLoader loader;

	@Autowired
	private JsonMapper mapper;


	@Test
	public void testSaveJoinJson() throws IOException {


		String jsons = StreamUtils.copyToString(loader.getResource("classpath:muiLine.json").getInputStream(), Charsets.UTF_8);

		String[] array= mapper.splitMuiJson(jsons);

		List<String> jsonList=new ArrayList<>();

		for(String str:array){

			jsonList.add(mapper.flatJson(str));
		}

		service.storeFullData(jsonList);

		service.storeFullData(jsonList);

		List<Map<String,Object>>  list=service.loadData();

//		assertEquals(list.size(),18);

		assertEquals(list.get(0).get("id"),"001");
		assertEquals(list.get(0).get("source"),"abc");

		assertEquals(list.get(0).get("id_global_thing"),1052);




	}


	@Test
	public void testSaveJson() throws IOException {


		String json = StreamUtils.copyToString(loader.getResource("classpath:singleLine.json").getInputStream(), Charsets.UTF_8);

		List<String> jsonList=new ArrayList<>();

		for(int i=0;i<100;i++){

			jsonList.add(json);
		}

		service.storeJsonData(jsonList);

		service.storeJsonData(jsonList);

		List<Map<String,Object>>  list=service.loadData();

		assertEquals(list.size(),200);

		assertEquals(list.get(0).get("id"),"0");
		assertEquals(list.get(0).get("source"),"source0");

	}

	@Test
	public void testSave(){


		List<SparkDataEntity> storeList=new ArrayList<>();


		long time= new Date().getTime();
		for(int i=0;i<100;i++){
			SparkDataEntity entity=new SparkDataEntity();
			entity.setId(String.valueOf(i));
			entity.setAction("action"+i);
			entity.setSource("source"+i);
			entity.setSourceType("sourceType"+String.valueOf(i%10));
			entity.setTimeStamp(new Timestamp((new Date(time+i*1000).getTime())));

//			Map<String,Object> node=new HashMap<>();
//			node.put("key"+i,i);
//			entity.setData(node);

			storeList.add(entity);
		}

		service.storeData(storeList);

		service.storeData(storeList);

		List<Map<String,Object>>  list=service.loadData();

		assertEquals(list.size(),200);

		assertEquals(list.get(0).get("id"),"0");
		assertEquals(list.get(0).get("source"),"source0");

	}

}
