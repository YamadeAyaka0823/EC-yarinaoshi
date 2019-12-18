package com.example.domain;

public class Temperature {
	
	/** 最小値 */
	public Min min;
	/** 最大値 */
	public Max max;
	
	public Min getMin() {
		return min;
	}
	public void setMin(Min min) {
		this.min = min;
	}
	public Max getMax() {
		return max;
	}
	public void setMax(Max max) {
		this.max = max;
	}
	
	@Override
	public String toString() {
		return "Temperature [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
