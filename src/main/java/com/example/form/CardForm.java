package com.example.form;

public class CardForm {
	
	/** 利用者ID */
	private String userId;
	/** 注文ON */
	private String orderNumber;
	/** 決済金額 */
	private String amount;
	/** クレジットカード番号 */
	private String cardNumber;
	/** カード有効期限(年) */
	private String cardExpYear;
	/** カード有効期限(月) */
	private String cardExpMonth;
	/** カード名義人 */
	private String cardName;
	/** セキュリティコード */
	private String cardCvv;
	
	public Integer getIntUserId() {
		return Integer.parseInt(userId);
	}
	
	public Integer getIntOrderNumber() {
		return Integer.parseInt(orderNumber);
	}
	
	public Integer getIntAmount() {
		return Integer.parseInt(amount);
	}
	
	public Integer getIntCardNumber() {
		return Integer.parseInt(cardNumber);
	}
	
	public Integer getIntCardExpYear() {
		return Integer.parseInt(cardExpYear);
	}
	
	public Integer getIntCardExpMonth() {
		return Integer.parseInt(cardExpMonth);
	}
	
	public Integer getInCardCvv() {
		return Integer.parseInt(cardCvv);
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardExpYear() {
		return cardExpYear;
	}
	public void setCardExpYear(String cardExpYear) {
		this.cardExpYear = cardExpYear;
	}
	public String getCardExpMonth() {
		return cardExpMonth;
	}
	public void setCardExpMonth(String cardExpMonth) {
		this.cardExpMonth = cardExpMonth;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
	
	@Override
	public String toString() {
		return "CardForm [userId=" + userId + ", orderNumber=" + orderNumber + ", amount=" + amount + ", cardNumber="
				+ cardNumber + ", cardExpYear=" + cardExpYear + ", cardExpMonth=" + cardExpMonth + ", cardName="
				+ cardName + ", cardCvv=" + cardCvv + "]";
	}
	
	
	

}
