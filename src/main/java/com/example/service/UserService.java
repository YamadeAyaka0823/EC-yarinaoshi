package com.example.service;

import java.util.List;

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
	
	/**
	 * 管理者画面でUser情報一覧のためのサービス.
	 * @return
	 */
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	/**
	 * 管理者画面でUser詳細取得のためのサービス.
	 * @param id
	 * @return
	 */
	public User load(Integer id) {
		return userRepository.load(id);
	}
	
	/**
	 * 管理者側でUserを削除するサービス.
	 * @param id
	 */
	public void deleteById(Integer id) {
		 userRepository.deleteById(id);
	}


}
