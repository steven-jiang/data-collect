package com.kii.ml.weka;

import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.classifiers.rules.DecisionTable;
import weka.core.Instance;
import weka.core.Instances;

public class R1Classify {


	private Classifier classifier;

	private DBLoader loader ;


	public R1Classify(String metaName) {

		loader = new DBLoader(metaName+".arff");

		Instances inst = loader.getInstance("SELECT * FROM "+metaName);

		classifier = new DecisionTable();

		try {
			classifier.buildClassifier(inst);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}


	public String doTest(  TestEntity entity)  {

		Instance inst = entity.getInstance(loader.getMetaInstance());

		try {

			double pred = classifier.classifyInstance(inst);
			double[] dist = classifier.distributionForInstance(inst);

			String value = pred == 0 ? "Yes" : "No";

			System.out.print(" play:"+value);

			System.out.print(" dist "+ Arrays.toString(dist));

			return value;
		}catch(Exception e){
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}



	}


}
