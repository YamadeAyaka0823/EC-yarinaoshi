package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AdministratorForm {
	
	/** 名前 */
	@NotBlank(message="名前を入力してください")
	private String name;
	/** メールアドレス */
	@Email(message="Eメールの形式が不正です")
	@Size(min=1, max=127, message="Eメールは1文字以上127文字以内で記載してください")
	private String mailAddress;
	/** パスワード */
	@Size(min=1, max=16, message="パスワードは1文字以上16文字以内で記載してください")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
		return "Administrator [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password + "]";
	}
	
	

}
