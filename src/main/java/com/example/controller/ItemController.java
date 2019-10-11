package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.form.ItemForm;
import com.example.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute
	public ItemForm setUpForm() {
		return new ItemForm();
	}
	
	/**
	 * 商品の一覧.
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model, ItemForm form) {
		if(form.getPageNumber() == null) {
			form.setPageNumber(1);
		}
		List<List<Item>> itemList = itemService.findAll(form);
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
	public String serch(String name, Model model, ItemForm form) {

		if(form.getPageNumber() == null || form.getPageNumber() >= 4 || form.getPageNumber() <= 0) {
			form.setPageNumber(1);
		}
		List<List<Item>> itemList = itemService.findByName(name, form);
		model.addAttribute("name", name);
		
		if(itemList.size() == 0) {
			model.addAttribute("message", "１件もありませんでした");
			return "item/list";
		}else {
			model.addAttribute("itemList", itemList);	
		}
//		return "item_list";
		
		if("1".equals(form.getPriceSort())) {
			List<List<Item>> itemList1 = itemService.findAll(form);
			model.addAttribute("itemList", itemList1);
			
		}else if("2".equals(form.getPriceSort())) {
			List<List<Item>> itemList1 = itemService.findAllHighPrice(form);
			model.addAttribute("itemList", itemList1);
		
			
		}
		return "item_list";
	}
	
	/**
	 * 商品詳細の検索.
	 * @param itemId
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		Item detail = itemService.load(id);
		model.addAttribute("detail", detail);
		
		List<List<Topping>> toppingList = itemService.findAllTopping();
		model.addAttribute("toppingList", toppingList);
		return "item_detail";
	}

}
