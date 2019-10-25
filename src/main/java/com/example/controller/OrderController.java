package com.example.controller;

import java.text.ParseException;
import java.time.LocalDate;

import javax.servlet.http.HttpSession;

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
	
	@Autowired
	private HttpSession session;
	
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
//		return "forward:/shoppingCart/showOrderItem";
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
		
		LocalDate localDate = LocalDate.now();
		LocalDate inputDate = LocalDate.parse(form.getDeliveryTime());
		boolean check = localDate.isBefore(inputDate);
		
		if(localDate.equals(inputDate)) {
			result.rejectValue("deliveryTime", "", "本日のお届けは出来ません");
		}
		
		if(check == false) {
			result.rejectValue("deliveryTime", "", "本日以降の日にちを指定してください");
		}
		
		if(result.hasErrors()) {
			return showOrder(form.getIntOrderId(),model);
		}
		
		if(form.getIntPaymentMethod().equals(1)) {
			orderService.load(form);
			return "order_finished";
		}
			return "forward:/card/confirm";
		
		
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
	
	/**
	 * APIの処理を実行
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/paymentApiResponse")
	public String confirmApiResponse(Model model) {
		String status = (String) session.getAttribute("card");
		System.out.println(status);
		if(status.equals("success")) {
			return "order_finished";
//			return "redirect:/order/load";
		}else {
			model.addAttribute("cardInfoError", "カード情報が不正です");
//			return "redirect:/order/showOrder";
			return "order_finished";
		}
	}

}
