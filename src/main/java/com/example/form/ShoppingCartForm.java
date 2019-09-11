package com.example.form;

import java.util.List;

public class ShoppingCartForm {
	
	/** アイテムID　*/
	private String itemId;
	/** サイズ */
	private String size;
	/** 数量 */
	private String quantity;
	/** トッピングリスト */
	private List<Integer> toppingList;
	
	public Integer getIntItemId() {
		return Integer.parseInt(itemId);
	}
	
	public Integer getIntQuantity() {
		return Integer.parseInt(quantity);
	}
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public List<Integer> getToppingList() {
		return toppingList;
	}
	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}
	
	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", size=" + size + ", quantity=" + quantity + ", toppingList="
				+ toppingList + "]";
	}
	
	
	
	

}
