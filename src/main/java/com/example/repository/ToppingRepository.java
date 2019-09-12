package com.example.repository;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
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
		String sql = "SELECT id, name, price_m, price_l FROM toppings";
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

}
