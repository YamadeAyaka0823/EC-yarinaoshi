package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;
import com.example.domain.Topping;

@Repository
public class ToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs,i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};
	
	/**
	 * トッピングの一覧を表すリポジトリ.
	 * @return
	 */
	public List<Topping> findAllTopping(){
		String sql = "SELECT id, name, price_m, price_l FROM toppings WHERE deleted = false ORDER BY id";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}
	

	
	/**
	 * トッピングをインサートするリポジトリ.
	 * @param orderTopping
	 * @return
	 */
	public void insert(OrderTopping orderTopping) {
		String sql = "INSERT INTO order_toppings(topping_id, order_item_id) VALUES(:toppingId, :orderItemId)";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderTopping.getOrderItemId()).addValue("toppingId", orderTopping.getToppingId());
		template.update(sql, param);
		
	}
	
	/**
	 * トッピングを削除するリポジトリ.
	 * @param id
	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		String sql = "DELETE FROM order_toppings WHERE order_item_id = :orderItemId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("orderItemId", orderItemId);
		template.update(sql, param);
	}
	
	/**
	 * 管理者画面でトッピングを追加するリポジトリ.
	 * @param topping
	 */
	public void toppingInsert(Topping topping) {
		String sql = "INSERT INTO toppings(name, price_m, price_l) VALUES(:name, :priceM, :priceL)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(topping);
		template.update(sql, param);
	}
	
	/**
	 * 管理者画面でトッピングを更新するリポジトリ.
	 * @param topping
	 */
	public void toppingUpdate(Topping topping) {
		String sql = "UPDATE toppings SET name = :name, price_m = :priceM, price_l = :priceL WHERE id = :id";
		SqlParameterSource param = new BeanPropertySqlParameterSource(topping);
		template.update(sql, param);
	}
	
	/**
	 * 管理者側でトッピングを削除するリポジトリ.
	 * @param id
	 */
	public void deleteById(Integer id) {
		String sql = "UPDATE toppings SET deleted = true WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	
	/**
	 * 管理者側でトッピングの曖昧検索するリポジトリ.
	 * @param name
	 * @return
	 */
	public List<Topping> findByToppingName(String name){
		String sql = "SELECT id, name, price_m, price_l FROM toppings WHERE name LIKE :name AND deleted = false";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Topping> toppingList = template.query(sql, param, TOPPING_ROW_MAPPER);
		return toppingList;
	}

}
