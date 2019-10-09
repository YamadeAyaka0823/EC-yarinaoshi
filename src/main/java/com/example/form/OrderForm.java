package com.example.form;



import javax.validation.constraints.NotBlank;



public class OrderForm {
	
	/** オーダーID */
	private String orderId;
	/** 宛先氏名 */
	@NotBlank(message="名前を入力してください")
	private String destinationName;
	/** 宛先Eメール */
	@NotBlank(message="メールアドレスを入力してください")
	private String destinationEmail;
	/** 宛先郵便番号 */
	@NotBlank(message="郵便番号を入力してください")
	private String destinationZipcode;
	/** 宛先住所 */
	@NotBlank(message="住所を入力してください")
	private String destinationAddress;
	/** 宛先TEL */
	@NotBlank(message="電話番号を入力してください")
	private String destinationTel;
	/** 配達日 */
	@NotBlank(message="配達日時を指定してください")
	private String deliveryTime;
	/** 配達時間 */
	private String deliveryHour;
	/** 支払方法 */
	@NotBlank(message="支払方法を選択してください")
	private String paymentMethod;
	/** 注文日 */
	private String orderDate;
	/** クレジットカード番号 */
	private String cardNumber;
	/** 有効期限(月) */
	private String expirationMonth;
	/** 有効期限(年) */
	private String expirationYear;
	/** カード名義人 */
	private String cardName;
	/** セキュリティコード */
	private String securityCode;
	

	
	public Integer getIntOrderId() {
		return Integer.parseInt(orderId);
	}
	
	public Integer getIntPaymentMethod() {
		return Integer.parseInt(paymentMethod);
	}
	
	public Integer getIntCardNumber() {
		return Integer.parseInt(cardNumber);
	}
	
	public Integer getIntSecurityCode() {
		return Integer.parseInt(securityCode);
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getDeliveryHour() {
		return deliveryHour;
	}

	public void setDeliveryHour(String deliveryHour) {
		this.deliveryHour = deliveryHour;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpirationMonth() {
		return expirationMonth;
	}

	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	public String getExpirationYear() {
		return expirationYear;
	}

	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	@Override
	public String toString() {
		return "OrderForm [orderId=" + orderId + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", deliveryHour=" + deliveryHour + ", paymentMethod=" + paymentMethod + ", orderDate=" + orderDate
				+ ", cardNumber=" + cardNumber + ", expirationMonth=" + expirationMonth + ", expirationYear="
				+ expirationYear + ", cardName=" + cardName + ", securityCode=" + securityCode + "]";
	}








	

	
	
	
	
	
	

}
