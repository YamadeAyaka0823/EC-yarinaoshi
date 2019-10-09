package com.example.form;

public class ItemForm {
	
	/** 名前 */
	private String name;
	/** 値段の順番 */
	private String priceSort;
	/** ページ数 */
	private Integer pageNumber;
	
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
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	@Override
	public String toString() {
		return "ItemForm [name=" + name + ", priceSort=" + priceSort + ", pageNumber=" + pageNumber + "]";
	}
	

	
//	public Integer getIntPriceSort() {
//		return Integer.parseInt(priceSort);
//	}




	

}
