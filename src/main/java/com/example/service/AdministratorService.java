package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Administrator;
import com.example.form.AdministratorForm;
import com.example.form.AdministratorLoginForm;
import com.example.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	/**
	 * 管理者を登録するサービス.
	 * @param form
	 */
	public void insert(AdministratorForm form) {
		Administrator administrator = new Administrator();
		administrator.setMailAddress(form.getMailAddress());
		administrator.setName(form.getName());
		administrator.setPassword(form.getPassword());
		administratorRepository.insert(administrator);
	}
	
	/**
	 * 管理者ログインのためのサービス.
	 * @param form
	 * @return
	 */
	public Administrator findByMailAddressAndPassword(AdministratorLoginForm form) {
		Administrator administrator = administratorRepository.findByMailAddressAndPassword(form.getMailAddress(), form.getPassword());
		return administrator;
	}

}
