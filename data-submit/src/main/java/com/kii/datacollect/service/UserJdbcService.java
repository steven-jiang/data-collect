package com.kii.datacollect.service;

import static org.apache.spark.sql.functions.col;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserJdbcService {


	@Autowired
	private SQLContext sqlContext;


	@Autowired
	private JavaSparkContext jsContext;


	@Value("${beehive.jdbc.url}")
	private String url;




	public DataFrame  loadUserFromSql(DataFrame dataDF){

		Map<String, String> options = new HashMap<String, String>();
		options.put("url", url);
		options.put("dbtable", "beehive.global_thing");

		DataFrame thingDF = sqlContext.read().format("jdbc"). options(options).load();

		return dataDF.join(thingDF,col("target").equalTo(col("vendor_thing_id")),"left_outer");

	}

}
