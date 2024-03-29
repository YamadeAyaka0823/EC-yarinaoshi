package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.repository.GetAutoCompleteRepository;

import net.arnx.jsonic.JSON;



@Controller
@RequestMapping("/getAutoComplete")
public class GetAutoCompleteController {
	
	@Autowired
	private GetAutoCompleteRepository getAutoCompleteRepository;
	
	/**
	 * 商品のオートコンプリート.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/list")
	public String getAutoComplete() {
		List<String> nameList = getAutoCompleteRepository.getAllNames();
		return JSON.encode(nameList);
	}
	
	/**
	 * トッピングのオートコンプリート.
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/toppingList")
	public String getToppingAutoComplete() {
		List<String> toppingNameList = getAutoCompleteRepository.getAllToppingNames();
		return JSON.encode(toppingNameList);
	}

}
