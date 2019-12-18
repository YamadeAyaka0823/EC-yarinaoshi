package com.example.domain;

public class Min {
	
	/** 最低気温 */
	public String celsius;

	public String getCelsius() {
		return celsius;
	}

	public void setCelsius(String celsius) {
		this.celsius = celsius;
	}

	@Override
	public String toString() {
		return "Min [celsius=" + celsius + "]";
	}
	
	

}
