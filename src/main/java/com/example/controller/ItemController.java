package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;

import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 商品の一覧.
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Item> itemList = itemService.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list";
	}
	
	/**
	 * 名前で商品検索.
	 * @param name
	 * @param model
	 * @return
	 */
	@RequestMapping("/serch")
	public String serch(String name, Model model) {
		List<Item> itemList = itemService.findByName(name);
		model.addAttribute("itemList", itemList);
		return list(model);
	}

}
