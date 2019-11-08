package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AdministratorLoginForm {
	
	/** メールアドレス */
	@Email(message="Eメールの形式が不正です")
	@Size(min=1, max=127, message="1文字以上127文字以内で記載してください")
	private String mailAddress;
	/** パスワード */
	@Size(min=1, max=16, message="1文字以上16文字以内で記載してください")
	private String password;
	
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "AdministratorLoginForm [mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	

	

}
