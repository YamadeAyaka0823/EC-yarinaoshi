package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GetAutoCompleteRepository {
	
	private static final RowMapper<String> NAME_ROW_MAPPER = (rs,i) -> {
		String name = rs.getString("name");
		return name;
	};
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	/**
	 * 商品の名前.
	 * @return
	 */
	public List<String> getAllNames(){
		List<String> allNames = jdbcTemplate.query("SELECT name FROM items WHERE deleted = false", NAME_ROW_MAPPER);
		return allNames;
	}
	
	/**
	 * トッピングの名前.
	 * @return
	 */
	public List<String> getAllToppingNames(){
		List<String> allToppingNames = jdbcTemplate.query("SELECT name FROM toppings WHERE deleted = false", NAME_ROW_MAPPER);
		return allToppingNames;
	}

}
