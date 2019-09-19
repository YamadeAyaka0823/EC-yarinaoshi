package com.example.form;



/**
 * ログイン用フォーム.
 * @author ayaka.yamade
 *
 */
public class LoginForm {
	
	/** メールアドレス */

	private String email;
	/** パスワード */

	private String password;
	
	/** 確認用パスワード */
	private String checkPassword;

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

	public String getCheckPassword() {
		return checkPassword;
	}

	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password + ", checkPassword=" + checkPassword + "]";
	}
	

	
	
	

	
	
	

}
