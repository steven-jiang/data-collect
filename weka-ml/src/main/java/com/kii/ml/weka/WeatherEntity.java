package com.kii.ml.weka;

import weka.core.DenseInstance;
import weka.core.Instance;

public class WeatherEntity {


	/*
	@attribute outlook {sunny, overcast, rainy}
@attribute temperature {hot, mild, cool}
@attribute humidity {high, normal}
@attribute windy {FALSE, TRUE}
@attribute play {Yes, No}
	 */

	private String outlook;

	private String temperature;

	private String humidity;

	private String windy;

	private String play;

	public String getOutlook() {
		return outlook;
	}

	public Instance  getInstance(){

		Instance inst=new DenseInstance(5);

		inst.setValue(0,outlook);
		inst.setValue(1,temperature);
		inst.setValue(2,humidity);
		inst.setValue(3,windy);
		inst.setValue(4,play);

		return inst;


	}

	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWindy() {
		return windy;
	}

	public void setWindy(String windy) {
		this.windy = windy;
	}

	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
	}
}

