package com.example.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;

import com.example.form.OrderForm;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 注文確定をするために1件検索するサービス.
	 * @param form
	 * @throws ParseException 
	 */
	public void load(OrderForm form) throws ParseException {
		 Order order = orderRepository.deepLoad(form.getIntOrderId());
		 
		 order.setDestinationAddress(form.getDestinationAddress());
		 order.setDestinationEmail(form.getDestinationEmail());
		 order.setDestinationName(form.getDestinationName());
		 order.setDestinationTel(form.getDestinationTel());
		 order.setDestinationZipcode(form.getDestinationZipcode());
		 order.setPaymentMethod(form.getIntPaymentMethod());
		 
		 int status;
		 if(form.getIntPaymentMethod().equals(1)) {
			 status = 1;
		 }else {
			 status = 2;
		 }
		 order.setStatus(status);
		 
		 String delivery = form.getDeliveryTime() + " " + form.getDeliveryHour();
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh");
		 Date parsedDate = dateFormat.parse(delivery);
		 Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		 order.setDeliveryTime(timestamp);
		 
		 Date date = new Date();
		 order.setOrderDate(date);
		 
		 
		 int totalPrice = order.getCalcTotalPrice() + order.getTax();
		 order.setTotalPrice(totalPrice);
		 
		 orderRepository.update(order);
	}

}
