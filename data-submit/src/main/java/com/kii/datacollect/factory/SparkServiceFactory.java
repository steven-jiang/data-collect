package com.kii.datacollect.factory;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkServiceFactory {


	private SQLContext  sqlContext;

	private JavaSparkContext sc;

	@PostConstruct
	public void init(){


		SparkConf conf = new SparkConf().setAppName("demo").setMaster("local");
		sc = new JavaSparkContext(conf);
		sqlContext = new SQLContext(sc);

	}

	@Bean
	public SQLContext  getSparkSqlContext(){
		return sqlContext;
	}




	@Bean
	public JavaSparkContext  getSparkContext(){
		return sc;
	}
}
