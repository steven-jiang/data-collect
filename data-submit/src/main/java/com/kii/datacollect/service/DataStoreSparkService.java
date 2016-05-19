package com.kii.datacollect.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;
import org.apache.spark.sql.types.StructType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kii.datacollect.store.SparkDataEntity;

@Component
public class DataStoreSparkService {

	@Autowired
	private SQLContext  sqlContext;


	@Autowired
	private UserJdbcService  userService;

	@Autowired
	private JavaSparkContext  jsContext;


	public void storeData(List<SparkDataEntity> entitys){


		JavaRDD<SparkDataEntity>  entityList=jsContext.parallelize(entitys);

		DataFrame datas = sqlContext.createDataFrame(entityList, SparkDataEntity.class);

		StructType type=datas.schema();

		datas.registerTempTable("datas");

		DataFrame all = sqlContext.sql("SELECT id FROM datas  ");

		datas.write().mode(SaveMode.Append).parquet("./build/data.parquet");

	}

	public void storeFullData(List<String> entitys){


		JavaRDD<String>  entityList=jsContext.parallelize(entitys);

		DataFrame datas = sqlContext.read().json(entityList);

		datas=userService.loadUserFromSql(datas);

		datas.write().mode(SaveMode.Overwrite).parquet("./build/data.parquet");

	}


	public void storeJsonData(List<String> entitys){


		JavaRDD<String>  entityList=jsContext.parallelize(entitys);

		DataFrame datas = sqlContext.read().json(entityList);

		StructType type=datas.schema();

		datas.registerTempTable("datas");

		DataFrame all = sqlContext.sql("SELECT id FROM datas  ");

		datas.write().mode(SaveMode.Append).parquet("./build/data.parquet");

	}



	public List<Map<String,Object>> loadData(){

		DataFrame parquetFile = sqlContext.read().parquet("./build/data.parquet");

		parquetFile.registerTempTable("dataList");

		DataFrame all = sqlContext.sql("SELECT id,source,id_global_thing FROM dataList  ");

		List<Map<String,Object>>  result=new ArrayList<>();

		all.collectAsList().forEach((o)->{

			Map<String,Object> r=new HashMap<>();
			r.put("id",o.get(0));
			r.put("source",o.get(1));
			r.put("id_global_thing",o.get(2));
			result.add(r);
		});

		return result;
	}


}
