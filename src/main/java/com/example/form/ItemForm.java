package com.example.form;

public class ItemForm {
	
	/** 名前 */
	private String name;
	/** 値段の順番 */
	private String priceSort;
	
	public Integer getIntPriceSort() {
		return Integer.parseInt(priceSort);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(String priceSort) {
		this.priceSort = priceSort;
	}

	@Override
	public String toString() {
		return "ItemForm [name=" + name + ", priceSort=" + priceSort + "]";
	}


	

}
