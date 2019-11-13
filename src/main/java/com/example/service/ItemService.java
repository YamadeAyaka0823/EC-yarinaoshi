package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.form.AdministratorToppingAddForm;
import com.example.form.ItemForm;
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
	public List<List<Item>> findAll(ItemForm form){
		return arrayTable(itemRepository.findAll(form.getPageNumber()));
	}
	
	/**
	 * 商品の全件検索を価格の高い順に検索するサービス.
	 * @return
	 */
	public List<List<Item>> findAllHighPrice(ItemForm form){
		return arrayTable(itemRepository.findAllHighPrice(form.getPageNumber()));
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
	public List<List<Item>> findByName(String name, ItemForm form){
		return arrayTable(itemRepository.findByName(name, form.getPageNumber()));
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
	
	/**
	 * 管理者画面で商品一覧を表示するためのサービス(ページング機能なし).
	 * @return
	 */
	public List<List<Item>> findAllNotPagenation(){
        return arrayTable(itemRepository.findAllNotPagenation());
	}
	
	/**
	 * 管理者側で商品内容を更新するためのサービス.
	 * @param form
	 */
	public void itemUpdate(Item item) {
		itemRepository.itemUpdate(item);
	}
	
	/**
	 * 管理者側で商品を削除するサービス.
	 * @param id
	 */
	public void itemDeleteById(Integer id) {
		itemRepository.itemDeleteById(id);
	}
	
	/**
	 * 管理者側で商品を追加するサービス.
	 * @param form
	 */
	public void itemInsert(Item item) {
		itemRepository.itemInsert(item);
	}
	
	/**
	 * 管理者側で商品の曖昧検索するサービス.
	 * @param form
	 * @return
	 */
	public List<List<Item>> findByItemName(String name){
		return arrayTable(itemRepository.findByItemName(name));
	}
	
	/**
	 * 管理者側でトッピングの一覧を表示するサービス.
	 * @return
	 */
	public List<List<Topping>> AdministratorFindAllTopping(){
		return arrayTable2(toppingRepository.findAllTopping());
	}
	
	/**
	 * 管理者側でトッピングの追加をするサービス.
	 * @param form
	 */
	public void toppingInsert(AdministratorToppingAddForm form) {
		Topping topping = new Topping();
		topping.setName(form.getName());
		topping.setPriceM(form.getIntPriceM());
		topping.setPriceL(form.getIntPriceL());
		toppingRepository.toppingInsert(topping);
	}
	
	/**
	 * 管理者側でトッピングの更新をするサービス.
	 * @param form
	 */
	public void toppingUpdate(AdministratorToppingAddForm form) {
		Topping topping = new Topping();
		topping.setId(form.getIntId());
		topping.setName(form.getName());
		topping.setPriceM(form.getIntPriceM());
		topping.setPriceL(form.getIntPriceL());
		toppingRepository.toppingUpdate(topping);
	}
	
	/**
	 * 管理者側でトッピングを削除するサービス.
	 * @param id
	 */
	public void deleteById(Integer id) {
		toppingRepository.deleteById(id);
	}
	
	/**
	 * 管理者側でトッピングの曖昧検索するサービス.
	 * @param form
	 * @return
	 */
	public List<List<Topping>> findByToppingName(String name){
		List<List<Topping>> toppingList = arrayTable2(toppingRepository.findByToppingName(name));
		return toppingList;
	}
	


}
