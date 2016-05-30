package com.kii.ml.weka;

import java.io.IOException;

import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.DatabaseLoader;

public class DBLoader {


	private DatabaseLoader  dbLoader;

	private ArffLoader   metaLoader;

	public DBLoader(){

		dbLoader=getLoader();

		metaLoader=getMetaLoader();
	}

	public Instances getInstance(){


		dbLoader.setQuery("SELECT * FROM beehive.ml_weather_nominal");


		try {
			Instances  instances= dbLoader.getDataSet();


			Instances  metaInst=metaLoader.getDataSet();


			for(int i=0;i<metaInst.numAttributes();i++){

				Attribute  attribute=metaInst.attribute(i);

				instances.replaceAttributeAt(attribute,i);
			}

			instances.setClassIndex(4);

			return instances;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}

	}

	private static DatabaseLoader getLoader(){

		try {

			DatabaseLoader  loader=new DatabaseLoader();
			loader.setPassword("beehive");
			loader.setUser("beehive");
			loader.setUrl("jdbc:mysql://localhost/beehive?Unicode=true&characterEncoding=utf8");
			loader.connectToDatabase();

			return loader;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}


	private static ArffLoader getMetaLoader(){

		try {

			ArffLoader  loader=new ArffLoader();

			loader.setSource(DBLoader.class.getResourceAsStream("weather.arff"));

			return loader;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}


}
