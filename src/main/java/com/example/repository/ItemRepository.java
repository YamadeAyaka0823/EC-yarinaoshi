package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;


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
	public List<Item> findAll(){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items ORDER BY price_m";
		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;
	}
	
	/**
	 * 名前で商品検索するリポジトリ.
	 * @param name
	 * @return
	 */
	public List<Item> findByName(String name){
		String sql = "SELECT id, name, description, price_m, price_l, image_path, deleted FROM items WHERE name LIKE :name ORDER BY price_m";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> itemList = template.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
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
	


}