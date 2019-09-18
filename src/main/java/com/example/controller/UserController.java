package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.domain.User;

import com.example.form.UserForm;
import com.example.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	

	
	@Autowired
	private UserService userService;
	

	
	/**
	 * ログイン画面.
	 * @return ログイン画面遷移
	 */
	@RequestMapping("toLogin")
	public String toLogin(Model model,@RequestParam(required = false) String error) {
		
		System.err.println("login error:" + error);
		if (error != null) {
			
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが不正です。");
			
		}
		return "login";
	}
	
	/**
	 * ユーザ登録画面.
	 * @return ユーザ登録画面遷移
	 */
	@RequestMapping("/register")
	public String register() {
		return "register_user";
	}
	
	@RequestMapping("/insert")
	public String insert(@Validated UserForm form, BindingResult result) {
		
		if(!form.getPassword().equals(form.getCheckPassword())) {
			result.rejectValue("password",null, "パスワードが一致しません");
			result.rejectValue("checkPassword", "", "");
		}
		User user = userService.findByEmail(form);
		if(user != null) {
			result.rejectValue("mail",null,"そのメールアドレスは既に登録されています");
		}
		
		if(result.hasErrors()) {
			return register();
		}
		User userDomain = new User();
		BeanUtils.copyProperties(form, userDomain);
		
		userService.insert(userDomain);
		
		return "redirect:/user/toLogin";
	}
	
	

}
