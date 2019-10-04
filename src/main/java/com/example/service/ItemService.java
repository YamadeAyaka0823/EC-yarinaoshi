package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

@Service
@Transactional
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private ToppingRepository toppingRepository;
	
	/**
	 * 全件検索を行うためのサービス.
	 * @return
	 */
	public List<List<Item>> findAll(Integer pageNumber){
		return arrayTable(itemRepository.findAll(pageNumber));
	}
	
	/**
	 * 商品の全件検索を価格の高い順に検索するサービス.
	 * @return
	 */
	public List<List<Item>> findAllHighPrice(){
		return arrayTable(itemRepository.findAllHighPrice());
	}
	
	public List<List<Item>> arrayTable(List<Item> item){
		List<Item> item3List = new ArrayList<>();
		
		List<List<Item>> itemAllList = new ArrayList<>();
		
		for(int i = 1; i <= item.size(); i++) {
			item3List.add(item.get(i-1));
			
			if((i % 3) == 0) {
				itemAllList.add(item3List);
				item3List = new ArrayList<>();
			}
		}
		if(item3List.size() == 0) {
			return itemAllList;
		}
		itemAllList.add(item3List);
		return itemAllList;
	}
	
	/**
	 * 名前で商品検索するリポジトリ.
	 * @param form
	 * @return
	 */
	public List<List<Item>> findByName(String name, Integer pageNumber){
		return arrayTable(itemRepository.findByName(name, pageNumber));
	}
	
	/**
	 * 商品詳細を検索するためのサービス.
	 * @param itemId
	 * @return
	 */
	public Item load(Integer id) {
		return itemRepository.load(id);
	}
	
	/**
	 * トッピングの一覧を表すサービス.
	 * @return
	 */
	public List<List<Topping>> findAllTopping(){
		return arrayTable2(toppingRepository.findAllTopping());
	}
	
	public List<List<Topping>> arrayTable2(List<Topping> topping){
		List<Topping> topping3List = new ArrayList<>();
		
		List<List<Topping>> toppingAllList = new ArrayList<>();
		
		for(int i = 1; i <= topping.size(); i++) {
			topping3List.add(topping.get(i-1));
			
			if((i % 3) == 0) {
				toppingAllList.add(topping3List);
				topping3List = new ArrayList<>();
			}
		}
		if(topping3List.size() == 0) {
			return toppingAllList;
		}
		toppingAllList.add(topping3List);
		return toppingAllList;
	}
	


}
