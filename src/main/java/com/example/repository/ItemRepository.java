	package com.example.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.OrderItem;


@Repository
public class ItemRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs,i) -> {
		Item item = new Item();
		item.setDeleted(rs.getBoolean("deleted"));
		item.setDescription(rs.getString("description"));
		item.setId(rs.getInt("id"));
		item.setImagePath(rs.getString("image_path"));
		item.setName(rs.getString("name"));
		item.setPriceL(rs.getInt("price_l"));
		item.setPriceM(rs.getInt("price_m"));
		return item;
	};
	
	/**
	 * 商品の全件検索を行うリポジトリ.
	 * @return
	 */
	public List<Item> findAll(Integer pageNumber){
		int offset = (pageNumber - 1) * 6;
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m LIMIT 6 OFFSET " + offset;
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 商品の全件検索を価格の高い順に検索するリポジトリ.
	 * @return
	 */
	public List<Item> findAllHighPrice(Integer pageNumber){
		int offset = (pageNumber - 1) * 6;
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m DESC LIMIT 6 OFFSET " + offset;
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 名前で商品検索するリポジトリ(ページング機能あり).
	 * @param name
	 * @return
	 */
	public List<Item> findByName(String name, Integer pageNumber){
		int offset = (pageNumber - 1) * 6;
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name LIKE :name ORDER BY price_m LIMIT 6 OFFSET " + offset;
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 名前で商品検索するリポジトリ(ページング機能なし).
	 * @param name
	 * @return
	 */
	public Integer findByName2(String name){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name LIKE :name ORDER BY price_m ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		Integer size = itemList.size();
		return size;
	}
	
	/**
	 * 商品詳細を検索するためのリポジトリ.
	 * @param itemId
	 * @return
	 */
	public Item load(Integer id) {
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		if(itemList.size() == 0) {
			return null;
		}
		return itemList.get(0);
	}
	
	private SimpleJdbcInsert insert;
	
	/**
	 * INSERT時に採番されたIDを取得する方法.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * 商品をDBにインサートするリポジトリ.
	 * @param orderItem
	 * @return
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		Number key = insert.executeAndReturnKey(param);
		orderItem.setId(key.intValue());
		return orderItem;
	}
	
	/**
	 * IDでOrderItemを削除するリポジトリ.
	 * @param id
	 */
	public void deleteById(Integer id) {
		String sql = "DELETE FROM order_items WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	public void update(OrderItem orderItem) {
		String sql = "UPDATE order_items SET order_id = :orderId WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderId", orderItem.getOrderId()).addValue("id", orderItem.getId());
		template.update(sql, param);
	}
	
	/**
	 * 管理者画面で商品一覧を表示するためのリポジトリ(ページング機能なし).
	 * @return
	 */
	public List<Item> findAllNotPagenation(){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE deleted = false ORDER BY id";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 管理者側で商品内容を更新するリポジトリ.
	 * @param item
	 */
	public void itemUpdate(Item item) {
		String sql = "UPDATE items SET name = :name, description = :description, price_m = :priceM, price_l = :priceL, image_path = :imagePath, deleted = :deleted WHERE id = :id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
	}
	
	/**
	 * 管理者側で商品を削除するリポジトリ.
	 * @param id
	 */
	public void itemDeleteById(Integer id) {
		String sql = "UPDATE items SET deleted = true WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	/**
	 * 管理者側で商品を追加するリポジトリ.
	 * @param item
	 */
	public void itemInsert(Item item) {
		String sql = "INSERT INTO items(name, description, price_m, price_l, image_path, deleted) VALUES(:name, :description, :priceM, :priceL, :imagePath, :deleted)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(item);
		template.update(sql, param);
	}
	
	/**
	 * 管理者側で商品の曖昧検索するリポジトリ.
	 * @param name
	 * @return
	 */
	public List<Item> findByItemName(String name){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name LIKE :name AND deleted = false";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}
	


}
