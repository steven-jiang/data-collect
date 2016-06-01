package com.kii.ml.weka;

import java.util.ArrayList;
import java.util.List;

import weka.classifiers.Classifier;
import weka.classifiers.rules.DecisionTable;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.M5Rules;
import weka.classifiers.rules.OneR;
import weka.classifiers.rules.PART;
import weka.classifiers.rules.ZeroR;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;

public class ClassifyService {



	private String metaName;

	private String FILENAME="classify_train.dump";

	private Classifier classifier ;

	public enum ClassifierType{

		R1,R0,DecisionTable,PART,JRip,M5Rule;


	}

	private Classifier  getClassifierInst(ClassifierType type){

		switch (type){
			case R1:
				return new OneR();
			case R0:
				return new ZeroR();
			case PART:
				return new PART();
			case JRip:
				return new JRip();
			case M5Rule:
				return new M5Rules();
			case DecisionTable:
				return new DecisionTable();
			default:
				throw new IllegalArgumentException("invalid classsifier type");
		}
	}

	public ClassifyService(String metaName,ClassifierType  type) {


		this.metaName=metaName;

		classifier=getClassifierInst(type);

	}

	public void train(){

		DBLoader loader = new DBLoader(metaName,4);

		Instances inst = loader.getInstance();


		try {
			classifier.buildClassifier(inst);

			List<Object> list = new ArrayList<>();
			list.add(classifier);

			list.add(new Instances(inst,0));

			SerializationHelper.write(FILENAME, list);

		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}



	}


	public String doTest(  Object entity)  {

		try {

			List<Object> v = (List<Object>) SerializationHelper.read(FILENAME);
			Classifier classifier = (Classifier) v.get(0);
			Instances metaInst = (Instances) v.get(1);

			Instance inst = EntityConvert.getInstances(metaInst,entity);

			double pred = classifier.classifyInstance(inst);
			double[] dist = classifier.distributionForInstance(inst);

			System.out.println("pred:"+pred);

			Attribute clsAttr=metaInst.attribute("play");

			String value =  clsAttr.value((int)pred);

			System.out.print(" play:"+value);

			return value;
		}catch(Exception e){
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}



	}


}
