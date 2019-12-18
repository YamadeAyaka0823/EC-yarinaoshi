package com.example.domain;

public class Forecasts {
	
	/** 日付 */
	String date;
	/** 気温 */
	Temperature temperature;
	/** 画像 */
	Image image;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Temperature getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Forecasts [date=" + date + "]";
	}
	
	
	
	

}
