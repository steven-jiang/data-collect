package com.kii.ml.weka;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class WeatherNumEntity implements  TestEntity{

	private String outlook;

	private float temperature;

	private int humidity;

	private String windy;

	public Instance getInstance(Instances meta){

		Instance inst=new DenseInstance(5);
		inst.setDataset(meta);

//		inst.
		inst.setValue(0,outlook);
		inst.setValue(1,temperature);
		inst.setValue(2,humidity);
		inst.setValue(3,windy);
//		inst.setValue(4,null);

		return inst;


	}


	public String getOutlook() {
		return outlook;
	}

	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public String getWindy() {
		return windy;
	}

	public void setWindy(String windy) {
		this.windy = windy;
	}
}
