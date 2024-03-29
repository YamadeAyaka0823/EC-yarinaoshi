package com.example.form;

import javax.validation.constraints.NotBlank;

public class AdministratorToppingAddForm {
	
	/** トッピングの名前 */
	@NotBlank(message="トッピング名を入れてください")
	private String name;
	/** 価格M */
	@NotBlank(message="値段を入れてください")
	private String priceM;
	/** 価格L */
	@NotBlank(message="値段を入れてください")
	private String priceL;
	
	public Integer getIntPriceM() {
		return Integer.parseInt(priceM);
	}
	
	public Integer getIntPriceL() {
		return Integer.parseInt(priceL);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPriceM() {
		return priceM;
	}
	public void setPriceM(String priceM) {
		this.priceM = priceM;
	}
	public String getPriceL() {
		return priceL;
	}
	public void setPriceL(String priceL) {
		this.priceL = priceL;
	}
	
	@Override
	public String toString() {
		return "AdministratorToppingAddForm [name=" + name + ", priceM=" + priceM + ", priceL=" + priceL + "]";
	}
	
	
	
	

}
