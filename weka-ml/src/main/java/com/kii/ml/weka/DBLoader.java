package com.kii.ml.weka;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.DatabaseLoader;

public class DBLoader {


	private DatabaseLoader  dbLoader;

	private ArffLoader   metaLoader;

	public DBLoader(String metaFileName){

		metaLoader=getMetaLoader(metaFileName);

		dbLoader=getLoader();

	}

	public Instances getMetaInstance(){

		try {
			Instances meta= 	metaLoader.getDataSet();

			meta.setClassIndex(4);

			return meta;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	public Instances getInstance(String sql){




		dbLoader.setQuery(sql);


		try {
			Instances  instances= dbLoader.getDataSet();
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

			URL url=DBLoader.class.getClassLoader().getResource("weka/experiment/localDatabaseUtils.props");

			loader.setCustomPropsFile(new File(url.toURI()));
			loader.setPassword("beehive");
			loader.setUser("beehive");
			loader.setUrl("jdbc:mysql://localhost/beehive?Unicode=true&characterEncoding=utf8&useSSL=false");

			loader.connectToDatabase();
			return loader;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}


	private static ArffLoader getMetaLoader(String metaFileName){

		try {

			ArffLoader  loader=new ArffLoader();

			loader.setSource(DBLoader.class.getResourceAsStream(metaFileName));

			return loader;

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}


}
