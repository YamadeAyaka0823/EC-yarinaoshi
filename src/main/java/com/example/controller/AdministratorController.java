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
import com.example.domain.User;
import com.example.form.AdministratorForm;
import com.example.form.AdministratorItemAddForm;
import com.example.form.AdministratorItemUpdateForm;
import com.example.form.AdministratorLoginForm;
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
	public String itemUpdate(AdministratorItemUpdateForm form, BindingResult result, Integer id, Model model) throws IOException {

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
			item.setId(form.getIntId());
			item.setDeleted(form.getDeleted());
			item.setName(form.getName());
			item.setDescription(form.getDescription());
			item.setPriceM(form.getIntPriceM());
			item.setPriceL(form.getIntPriceL());
			item.setImagePath(filename);
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
	public String itemAdd(AdministratorItemAddForm form, Model model) {
		itemService.itemInsert(form);
		return itemList(model);
	}
	


}
