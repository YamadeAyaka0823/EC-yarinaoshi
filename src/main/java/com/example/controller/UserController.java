package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.form.UserForm;
import com.example.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@ModelAttribute
	public UserForm setUpForm() {
		return new UserForm();
	}
	
	@ModelAttribute
	public LoginForm setUpForm2() {
		return new LoginForm();
	}
	
	@Autowired
	private UserService userService;
	
	/**
	 * ログイン画面.
	 * @return ログイン画面遷移
	 */
	@RequestMapping("")
	public String index() {
		System.out.println("a");
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
		
		if(result.hasErrors()) {
			return register();
		}
		userService.insert(form);
		return index();
	}
	
	/**
	 * ログイン.
	 * @param form
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@Validated LoginForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return index();
		}
		

		User user = userService.findByEmailAndPassword(form);
		model.addAttribute("user", user);
		return "forward:/item/list";
	}
	
	

}
