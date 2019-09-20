package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.ItemRepository;
import com.example.repository.OrderRepository;

@Controller
@RequestMapping("/checkSession")
public class CheckSessionController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@RequestMapping("/check")
	public String check(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		int sessionId = (int) session.getAttribute("value");
		int userId = loginUser.getUser().getId();
		List<Order> orderSessionList = orderRepository.findByStatusAndUserId(0, sessionId);
		List<Order> orderUserList = orderRepository.findByStatusAndUserId(0, userId);
		
		if(orderSessionList.size() == 0 && orderUserList.size() == 0) {
			return "redirect:/user/toLogin";
		}
		
		if(orderSessionList.size() == 0 && orderUserList.size() != 0) {
			return "redirect:/user/toLogin";
		}
		
		if(orderUserList.size() == 0) {
			Order orderSession = orderSessionList.get(0);
			orderSession.setUserId(userId);
			orderRepository.update(orderSession);
			return "redirect:/item/list";
		}else {
			Order orderUser = orderUserList.get(0);
			Order orderSession = orderSessionList.get(0);
			orderRepository.deleteByOrderId(orderSession.getId());
			for(OrderItem orderItemSession : orderSession.getOrderItemList()) {
				orderItemSession.setOrderId(orderUser.getId());
				itemRepository.update(orderItemSession);
			}
			return "redirect:/item/list";
		}
	}

}
