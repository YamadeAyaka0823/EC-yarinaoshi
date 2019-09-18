package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;

import com.example.repository.ItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class OrderItemService {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private ToppingRepository toppingRepository;
	
	
	/**
	 * ショッピングカートに商品を入れるためのサービス.
	 * @param form
	 * @param status
	 * @param userId
	 * @param order
	 * @param orderItem
	 * @param orderTopping
	 */
	public void insert(Order order, OrderItem orderItem) {
		
		order.setStatus(0);
		List<Order> orderList = orderRepository.findByStatusAndUserId(order.getStatus(), order.getUserId());
		
		if(orderList.size() == 0) {
			Order orderDomain = orderRepository.insert(order);
			orderItem.setOrderId(orderDomain.getId());
			
			OrderItem orderItemDomain = itemRepository.insert(orderItem);
			for(OrderTopping orderTopping : orderItem.getOrderToppingList()) {
				orderTopping.setOrderItemId(orderItemDomain.getId());
				toppingRepository.insert(orderTopping);
			}
		}else {
            Order orderDomain = orderList.get(0);
            orderItem.setOrderId(orderDomain.getId());
            OrderItem orderItemDomain = itemRepository.insert(orderItem);
            
            for(OrderTopping orderTopping : orderItem.getOrderToppingList()) {
            	orderTopping.setOrderItemId(orderItemDomain.getId());
            	toppingRepository.insert(orderTopping);
            	
            }
		}
		

	}
	
	/**
	 * 注文商品を検索するサービス.
	 * @param userId
	 * @param status
	 * @return
	 */
//	public List<Order> showOrder(Integer status, Integer userId) {
//		List<Order> orderList = orderRepository.findByStatusAndUserId(status, userId);
//		return orderList;
//	}
	
	/**
	 * 注文商品を1件検索するサービス.
	 * @param orderId
	 * @return
	 */
	public Order deepLoad(Integer id) {
		Order orderItems = orderRepository.deepLoad(id);
		return orderItems;
	}
	

}
