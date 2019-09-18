package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.repository.UserRepository;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * ユーザ登録するためのサービス.
	 * @param user
	 */
	public void insert(User user) {
//		User user = new User();
//		user.setAddress(form.getAddress());
//		user.setEmail(form.getEmail());
//		user.setName(form.getName());
//		user.setPassword(form.getPassword());
//		user.setTelephone(form.getTelephone());
//		user.setZipcode(form.getZipcode());
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		userRepository.insert(user);
	}
	
	/**
	 * ログインするための1件検索リポジトリ.
	 * @param form
	 * @return
	 */
	public User findByEmail(UserForm form) {
		return userRepository.findByEmail(form.getEmail());
	}


}
