package com.kii.ml.weka;

import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.classifiers.rules.OneR;
import weka.core.Instance;
import weka.core.Instances;

public class R1Classify {


	private Classifier classifier;


	public R1Classify() {


		DBLoader loader = new DBLoader();

		Instances inst = loader.getInstance();


		classifier = new OneR();

		try {
			classifier.buildClassifier(inst);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}


	public String doTest(WeatherEntity entity)  {

		Instance inst = entity.getInstance();

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
