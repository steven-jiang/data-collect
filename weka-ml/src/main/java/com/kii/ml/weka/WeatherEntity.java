package com.kii.ml.weka;

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

