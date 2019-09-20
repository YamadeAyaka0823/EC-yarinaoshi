package com.example.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.service.OrderService;

@Controller
@RequestMapping("/orderHistory")
public class OrderHistoryController {
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 注文履歴表示のためのメソッド.
	 * @param form
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/history")
	public String history(Model model, @AuthenticationPrincipal LoginUser loginUser){
		Integer userId = loginUser.getUser().getId();
		
		List<Order> orderList = orderService.findByStatusThan0UserId(userId);
		model.addAttribute("orderList", orderList);
		return "order_history";
	}

}
