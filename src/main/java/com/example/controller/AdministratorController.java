package com.example.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.domain.User;
import com.example.form.AdministratorForm;
import com.example.form.AdministratorItemAddForm;
import com.example.form.AdministratorItemUpdateForm;
import com.example.form.AdministratorLoginForm;
import com.example.form.AdministratorToppingAddForm;
import com.example.service.AdministratorService;
import com.example.service.ItemService;
import com.example.service.UserService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemService itemService;
	
	@ModelAttribute
	public AdministratorForm setUpForm() {
		return new AdministratorForm();
	}
	
	@ModelAttribute
	public AdministratorLoginForm setUpForm2() {
		return new AdministratorLoginForm();
	}
	
	@ModelAttribute
	public AdministratorToppingAddForm setUpForm3() {
		return new AdministratorToppingAddForm();
	}
	
	@ModelAttribute
	public AdministratorItemAddForm setUpForm4() {
		return new AdministratorItemAddForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "administrator/administratorRegister";
	}
	
	/**
	 * 管理者ログイン初期画面.
	 * @return
	 */
	@RequestMapping("/enter")
	public String enter() {
		return "administrator/administratorLogin";
	}
	
	/**
	 * 管理者を登録する.
	 * @param form
	 * @return
	 */
	@RequestMapping("/register")
	public String register(@Validated AdministratorForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return index();
		}
		administratorService.insert(form);
		return "administrator/administratorLogin";
	}
	
	/**
	 * 管理者画面でUser情報一覧取得.
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<User> userList = userService.findAll();
		model.addAttribute("userList", userList);
		return "administrator/administratorList";
	}
	
	/**
	 * 管理者画面でUserを削除.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer id, Model model) {
		userService.deleteById(id);
		return list(model);
	}
	
	/**
	 * 管理者画面でUser詳細取得.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		User user = userService.load(id);
		model.addAttribute("user", user);
		return "administrator/administratorDetail";
	}
	
	/**
	 * 管理者ログイン.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(@Validated AdministratorLoginForm form, BindingResult result, Model model) {
		Administrator administrator = administratorService.findByMailAddressAndPassword(form);
		
		if(administrator == null) {
			model.addAttribute("error", "メールアドレスまたはパスワードが不正です");
			return enter();
		}
		
		if(result.hasErrors()) {
			return enter();
		}
		return list(model);
	}
	
	/**
	 * 管理者ログアウト.
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		return enter();
	}
	
	/**
	 * 商品かトッピング関連かの初期画面.
	 * @return
	 */
	@RequestMapping("/itemOrTopping")
	public String itemOrTopping() {
		return "administrator/item_or_topping";
	}
	
	/**
	 * 商品一覧.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemList")
	public String itemList(Model model) {
		List<List<Item>> itemList = itemService.findAllNotPagenation();
		model.addAttribute("itemList", itemList);
		return "administrator/administratorItem_list";
	}
	
	/**
	 * 商品詳細.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemDetail")
	public String itemDetail(Integer id, Model model) {
		Item item = itemService.load(id);
		model.addAttribute("item", item);
		return "administrator/administratorItem_detail";
	}
	
	/**
	 * 商品更新の初期画面.
	 * @return
	 */
	@RequestMapping("/update")
	public String update(Integer button,Integer id, Model model) {
		
		if(button == 2) {
			Item item = itemService.load(id);
			model.addAttribute("item", item);
			return "administrator/itemUpdate";
		}else {
			itemService.itemDeleteById(id);
			return itemList(model);
		}
	}
	
	/**
	 * 商品内容変更ページ.
	 * @param form
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/itemUpdate")
	public String itemUpdate(Item item, Integer id, Model model) throws IOException {
		
		Path sourcePath = Paths.get("src/main/resources/static/imgConfirm/" + item.getImagePath());
		Path targetPath = Paths.get("src/main/resources/static/img/" + item.getImagePath());
		try {
			Files.move(sourcePath, targetPath);
		}catch(IOException e) {
			e.printStackTrace();
		}

		itemService.itemUpdate(item);
		return itemList(model);
	}
	
	/**
	 * 商品追加の初期画面.
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "/administrator/itemAdd";
	}
	
	/**
	 * 商品追加.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemAdd")
	public String itemAdd(@Validated AdministratorItemAddForm form, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return add();
		}
		
		int dot = form.getImagePath().getOriginalFilename().lastIndexOf(".");
		String extention = "";
		if(dot > 0) {
			extention = form.getImagePath().getOriginalFilename().substring(dot).toLowerCase();
		}
		String filename = form.getImagePath().getOriginalFilename();
		Path path = Paths.get("src/main/resources/static/img/" + filename);
		
		try(OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)){
			byte[] bytes = form.getImagePath().getBytes();
			os.write(bytes);
		}catch(IOException ex) {
			System.err.println(ex);
		}
		
		Item item = new Item();
		item.setName(form.getName());
		item.setDescription(form.getDescription());
		item.setPriceM(form.getIntPriceM());
		item.setPriceL(form.getIntPriceL());
		item.setImagePath(filename);
		item.setDeleted(form.getDeleted());
		itemService.itemInsert(item);
		return "redirect:/administrator/itemList";
	}
	
	/**
	 * 商品の曖昧検索.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/itemSearch")
	public String itemSearch(String name, Model model) {
		List<List<Item>> itemList = itemService.findByItemName(name);
		
		if(itemList.size() == 0) {
			model.addAttribute("error", "1件もありませんでした");
			return itemList(model);
		}else {
			model.addAttribute("itemList", itemList);
		}
		return "administrator/administratorItem_list";
	}
	
	/**
	 * トッピングの一覧.
	 * @param model
	 * @return
	 */
	@RequestMapping("/toppingList")
	public String toppingList(Model model) {
		List<List<Topping>> toppingList = itemService.AdministratorFindAllTopping();
		model.addAttribute("toppingList", toppingList);
		return "administrator/toppingList";
	}
	
	/**
	 * トッピングを追加するための初期画面.
	 * @return
	 */
	@RequestMapping("/toppingAdd")
	public String toppingAdd() {
		return "administrator/toppingAdd";
	}
	
	/**
	 * トッピングの追加.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/toppingInsert")
	public String toppingInsert(@Validated AdministratorToppingAddForm form, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return toppingAdd();
		}
		itemService.toppingInsert(form);
		return "redirect:/administrator/toppingList";
	}
	
	/**
	 * トッピングを更新するための初期画面.
	 * @return
	 */
	@RequestMapping("/toppingChange")
	public String toppingChange() {
		return "administrator/toppingUpdate";
	}
	
	/**
	 * トッピングを更新.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/toppingUpdate")
	public String toppingUpdate(AdministratorToppingAddForm form, Model model) {
		itemService.toppingUpdate(form);
		return toppingList(model);
	}
	
	/**
	 * トッピングを削除するための初期画面.
	 * @return
	 */
	@RequestMapping("/toppingDelete")
	public String toppingDelete() {
		return "administrator/toppingDelete";
	}
	
	/**
	 * トッピングを削除する.
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/toppingDeleteComplete")
	public String toppingDeleteComplete(Integer id, Model model) {
		itemService.deleteById(id);
		return toppingList(model);
	}
	
	/**
	 * トッピングを曖昧検索する.
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping("/findByToppingName")
	public String findByToppingName(String name, Model model) {
		List<List<Topping>> toppingList = itemService.findByToppingName(name);
		if(toppingList.size() == 0) {
			model.addAttribute("error", "１件もありません");
		}else {
			model.addAttribute("toppingList", toppingList);
		}
		return "administrator/toppingList";
	}
	
	/**
	 * 商品更新最終確認.
	 * @return
	 */
	@RequestMapping("/itemUpdateConfirm")
	public String itemUpdateConfirm(AdministratorItemUpdateForm form, Model model) {
		
		int dot = form.getImagePath().getOriginalFilename().lastIndexOf(".");
		String extention = "";
		if(dot > 0) {
			extention = form.getImagePath().getOriginalFilename().substring(dot).toLowerCase();
		}
		String filename = form.getImagePath().getOriginalFilename();
		Path path = Paths.get("src/main/resources/static/imgConfirm/" + filename);
		
		try(OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)){
			byte[] bytes = form.getImagePath().getBytes();
			os.write(bytes);
		}catch(IOException ex) {
			System.err.println(ex);
		}
		
		Item item = new Item();
		item.setId(form.getIntId());
		item.setDeleted(form.getDeleted());
		item.setName(form.getName());
		item.setDescription(form.getDescription());
		item.setPriceM(form.getIntPriceM());
		item.setPriceL(form.getIntPriceL());
		item.setImagePath(filename);
		
		model.addAttribute("item", item);
		return "administrator/itemUpdate_confirm";
	}
	

	


}
