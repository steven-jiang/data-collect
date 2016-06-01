package com.kii.ml.weka;

public class WeatherNumEntity {

	private String outlook;

	private float temperature;

	private int humidity;

	private String windy;

	private String play;

	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
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
