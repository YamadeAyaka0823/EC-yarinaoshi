package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザ登録用フォーム.
 * @author ayaka.yamade
 *
 */
public class UserForm {
	
	/** 名前 */
	@NotBlank(message="名前を入力してください")
	private String name;
	/** メールアドレス */
	@NotBlank(message="メールアドレスを入力してください")
	private String email;
	/** パスワード */
	@Pattern(regexp ="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,32}$", message="8文字以上32文字以内かつ半角数字、半角英字(大文字、小文字)を1つずつ使用し記入してください")
	private String password;
	/** 郵便番号 */
	@NotBlank(message="郵便番号を入力してください")
	private String zipcode;
	/** 住所 */
	@NotBlank(message="住所を入力してください")
	private String address;
	/** 電話番号 */
	@Pattern(regexp ="[0-9]{1,11}",message="電話番号を入力してください")
	private String telephone;
	/** 確認用パスワード */
	@NotBlank(message="確認用パスワードを入力してください")
	private String checkPassword;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}
	
	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", zipcode=" + zipcode
				+ ", address=" + address + ", telephone=" + telephone + ", checkPassword=" + checkPassword + "]";
	}
	
	
	
	

}
