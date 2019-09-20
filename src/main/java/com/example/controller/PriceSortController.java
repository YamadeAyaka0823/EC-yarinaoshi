package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/priceSort")
public class PriceSortController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 商品の一覧.
	 * @param model
	 * @return
	 */
	@RequestMapping("/price")
	public String price(ItemForm form, Model model) {
		
		if(form.getIntPriceSort() == 1) {
			List<List<Item>> itemList = itemService.findAll();
			model.addAttribute("itemList", itemList);
			
		}else if(form.getIntPriceSort() == 2) {
			List<List<Item>> itemList = itemService.findAllHighPrice();
			model.addAttribute("itemList", itemList);
			
		}
		return "item_list";
	}
	


}
