package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@Autowired
	private HttpSession session;
	
	@ModelAttribute
	public CardForm setUpForm() {
		return new CardForm();
	}
	
	
	/** カード決済APIサービス呼び出し */
	@RequestMapping(value="/card/confirm", method=RequestMethod.POST)
	public String confirm(CardForm form, Model model) {
//		System.out.println(form);
		if(!form.getPaymentMethod().equals(2)) {
			return "redirect:/order/load";
		}
		Card card = cardService.service(form);
		session.setAttribute("card", card.getStatus());
		return "redirect:/order/paymentApiResponse";
	}

}
