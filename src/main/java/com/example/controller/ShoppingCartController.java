package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.ShoppingCartForm;
import com.example.repository.OrderRepository;
import com.example.service.OrderItemService;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 商品をインサートする.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/addItemToCart")
	public String addItemToCart(ShoppingCartForm form,BindingResult result, @AuthenticationPrincipal LoginUser loginUser, Model model) {
		
		/** ログインしてない状態でカートに入れる */
		long seed = 1000;
		Random random = new Random(seed);
		int value = random.nextInt();

		Order order = new Order();
		order.setTotalPrice(0);
		
		int user_id = 0;
        if(loginUser == null) {
        	user_id = value;
        	order.setUserId(user_id);
        }else {
        	user_id = loginUser.getUser().getId();
        }
        order.setUserId(user_id);

		OrderItem orderItem = new OrderItem();
		char[] size = form.getSize().toCharArray();
		orderItem.setItemId(form.getIntItemId());
		orderItem.setSize(size[0]);
		orderItem.setQuantity(form.getIntQuantity());
		List<OrderTopping> orderToppingList = new ArrayList<>();
		
		if(form.getOrderToppingList() != null) {
			for(Integer toppingId : form.getOrderToppingList()) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setToppingId(toppingId);
				orderToppingList.add(orderTopping);
			}
		}
		
		orderItem.setOrderToppingList(orderToppingList);
		orderItemService.insert(order, orderItem);
		
		return "redirect:/shoppingCart/showOrderItem";
	}
	
	/**
	 * 注文商品を表示する.
	 * @param userId
	 * @param status
	 * @param model
	 * @return
	 */
	@RequestMapping("/showOrderItem")
	public String showOrderItem(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		int status = 0;
		
		/** ログインしてない状態でカートに入れる */
		long seed = 1000;
		Random random = new Random(seed);
		int value = random.nextInt();
		
		int userId = 0;
		if(loginUser == null) {
			userId = value;
		}else {
			userId = loginUser.getUser().getId();
		}
		
		List<Order> orderList = orderRepository.findByStatusAndUserId(status , userId);
		if(orderList.size() == 0) {
			Order order = new Order();
			order.setOrderItemList(new ArrayList<OrderItem>());
			model.addAttribute("order", order);
			
		}else {
			Order order = orderList.get(0);
			order = orderRepository.deepLoad(order.getId());
			model.addAttribute("order", order);
			
		}
		
		return "cart_list";
	}
	
	/**
	 * カートの中身の商品を削除する.
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer id) {
		orderItemService.delete(id);
		
		return "redirect:/shoppingCart/showOrderItem";
	}
	


}
