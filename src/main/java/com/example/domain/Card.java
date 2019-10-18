package com.example.domain;

public class Card {
	
	/** APIレスポンスステータス */
	private String status;
	/** APIレスポンスメッセージ */
	private String message;
	/** APIエラーコード */
	private String error_code;
	/** 利用者ID */
	private Integer user_id;
	/** 注文ON */
	private Integer order_number;
	/** 決済金額 */
	private Integer amount;
	/** クレジットカード番号 */
	private Integer card_number;
	/** カード有効期限(年) */
	private Integer card_exp_year;
	/** カード有効期限(月) */
	private Integer card_exp_month;
	/** カード名義人 */
	private String card_name;
	/** セキュリティコード */
	private Integer card_cvv;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError_code() {
		return error_code;
	}
	public void setError_code(String error_code) {
		this.error_code = error_code;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getOrder_number() {
		return order_number;
	}
	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getCard_number() {
		return card_number;
	}
	public void setCard_number(Integer card_number) {
		this.card_number = card_number;
	}
	public Integer getCard_exp_year() {
		return card_exp_year;
	}
	public void setCard_exp_year(Integer card_exp_year) {
		this.card_exp_year = card_exp_year;
	}
	public Integer getCard_exp_month() {
		return card_exp_month;
	}
	public void setCard_exp_month(Integer card_exp_month) {
		this.card_exp_month = card_exp_month;
	}
	public String getCard_name() {
		return card_name;
	}
	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}
	public Integer getCard_cvv() {
		return card_cvv;
	}
	public void setCard_cvv(Integer card_cvv) {
		this.card_cvv = card_cvv;
	}
	
	@Override
	public String toString() {
		return "Card [status=" + status + ", message=" + message + ", error_code=" + error_code + ", user_id=" + user_id
				+ ", order_number=" + order_number + ", amount=" + amount + ", card_number=" + card_number
				+ ", card_exp_year=" + card_exp_year + ", card_exp_month=" + card_exp_month + ", card_name=" + card_name
				+ ", card_cvv=" + card_cvv + "]";
	}
	

	

	

	

	
	

}
