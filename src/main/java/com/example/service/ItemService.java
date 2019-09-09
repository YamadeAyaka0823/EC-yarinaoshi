package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;

import com.example.repository.ItemRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 全件検索を行うためのサービス.
	 * @return
	 */
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	
	/**
	 * 名前で商品検索するリポジトリ.
	 * @param form
	 * @return
	 */
	public List<Item> findByName(String name){
		return itemRepository.findByName(name);
	}

}
