package com.example.domain;

public class Location {
	
	/** 都道府県 */
	String prefecture;
	/** 街 */
	String city;
	
	public String getPrefecture() {
		return prefecture;
	}
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Location [prefecture=" + prefecture + ", city=" + city + "]";
	}


	

}
