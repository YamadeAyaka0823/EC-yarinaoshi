package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.example.domain.Card;
import com.example.form.CardForm;

@Service
@Transactional
public class CardService {
	
//	@Autowired
	
	@Qualifier("cardRestTemplate")
	RestTemplate restTemplate;
	
	/** カード決済API リクエストURL */
	private static final String URL = "http://172.16.0.13:8080/web-api-sample/credit-card/payment";

	public Card service(CardForm form) {
		return restTemplate.getForObject(URL, Card.class, form);
	}
}
