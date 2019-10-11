package com.example.domain;

public class Card {
	
	/** 利用者ID */
	private Integer userId;
	/** 注文ON */
	private Integer orderNumber;
	/** 決済金額 */
	private Integer amount;
	/** クレジットカード番号 */
	private Integer cardNumber;
	/** カード有効期限(年) */
	private Integer cardExpYear;
	/** カード有効期限(月) */
	private Integer cardExpMonth;
	/** カード名義人 */
	private String cardName;
	/** セキュリティコード */
	private Integer cardCvv;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Integer getCardExpYear() {
		return cardExpYear;
	}
	public void setCardExpYear(Integer cardExpYear) {
		this.cardExpYear = cardExpYear;
	}
	public Integer getCardExpMonth() {
		return cardExpMonth;
	}
	public void setCardExpMonth(Integer cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public Integer getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(Integer cardCvv) {
		this.cardCvv = cardCvv;
	}
	
	@Override
	public String toString() {
		return "Card [userId=" + userId + ", orderNumber=" + orderNumber + ", amount=" + amount + ", cardNumber="
				+ cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth + ", cardName="
				+ cardName + ", cardCvv=" + cardCvv + "]";
	}
	
	

}
