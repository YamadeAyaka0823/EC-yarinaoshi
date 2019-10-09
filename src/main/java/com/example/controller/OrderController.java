package com.example.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Order;
import com.example.form.OrderForm;

import com.example.service.OrderItemService;
import com.example.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderItemService orderItemService;
	
	@ModelAttribute
	public OrderForm setUpForm() {
		return new OrderForm();
	}
	
	/**
	 * 注文確認画面表示.
	 * @return
	 */
	@RequestMapping("/")
	public String index() {
		return "order_confirm";
	}
	
	/**
	 * 注文確定のためのメソッド.
	 * @param form
	 * @param model
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/load")
	public String load(@Validated OrderForm form, BindingResult result, Model model) throws ParseException {
        System.out.println(form);
		if(result.hasErrors()) {
			return index();
		}
		
		orderService.load(form);
		model.addAttribute("form", form);
		return "order_finished";
	}
	
	/**
	 * 注文確認商品一覧のメソッド.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showOrder")
	public String showOrder(Integer id, Model model) {
		Order order = orderItemService.deepLoad(id);
		model.addAttribute("order", order);
		return "order_confirm";
	}

}
