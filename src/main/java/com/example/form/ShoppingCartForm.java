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
	private List<Integer> orderToppingList;
	/** 合計金額 */
	private String totalPrice;
	/** ユーザID */
	private String userId;
	
	public Integer getIntItemId() {
		return Integer.parseInt(itemId);
	}
	
	public Integer getIntQuantity() {
		return Integer.parseInt(quantity);
	}
	
	public Integer getIntTotalPrice() {
		return Integer.parseInt(totalPrice);
	}
	
	public Integer getIntUserId() {
		return Integer.parseInt(userId);
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

	public List<Integer> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<Integer> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "ShoppingCartForm [itemId=" + itemId + ", size=" + size + ", quantity=" + quantity
				+ ", orderToppingList=" + orderToppingList + ", totalPrice=" + totalPrice + ", userId=" + userId + "]";
	}




	

	
	
	
	

}
