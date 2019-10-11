package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Card;
import com.example.form.CardForm;
import com.example.service.CardService;

@Controller
@RequestMapping("")
public class CardController {
	
	@Autowired
	private CardService cardService;
	
	
	/** カード決済APIサービス呼び出し */
	@RequestMapping(value="/card/confirm", method=RequestMethod.POST)
	public String confirm(CardForm form, Model model) {
		System.out.println(form);
		Card card = cardService.service(form);
		model.addAttribute("form", card);
		return "card_confirm";
	}

}
