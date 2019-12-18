package com.example.domain;

public class Max {
	
	/** 最高気温 */
	public String celsius;

	public String getCelsius() {
		return celsius;
	}

	public void setCelsius(String celsius) {
		this.celsius = celsius;
	}

	@Override
	public String toString() {
		return "Max [celsius=" + celsius + "]";
	}
	
	
	
	

}
