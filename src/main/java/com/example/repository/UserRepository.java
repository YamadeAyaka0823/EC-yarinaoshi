package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

@Repository
public class UserRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<User> USER_ROW_MAPPER = (rs,i) ->{
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};
	
	/**
	 * ユーザ登録するためのリポジトリ.
	 * @param user
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		String sql = "INSERT INTO users(name, email, password, zipcode, address, telephone) VALUES(:name, :email, :password, :zipcode, :address, :telephone)";
		template.update(sql, param);
	}
	
	/**
	 * ログインするための1件検索リポジトリ.
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE email = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * 管理者画面でUser情報の一覧のためのリポジトリ.
	 * @return
	 */
	public List<User> findAll(){
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE deleted = false ORDER BY id";
		List<User> userList = template.query(sql, USER_ROW_MAPPER);
		return userList;
	}
	
	/**
	 * 管理者画面でUserの詳細情報取得のためのリポジトリ.
	 * @param id
	 * @return
	 */
	public User load(Integer id) {
		String sql = "SELECT id, name, email, password, zipcode, address, telephone FROM users WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if(userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
	
	/**
	 * 管理者画面でUserを削除するリポジトリ.
	 * @param id
	 */
	public void deleteById(Integer id) {
		String sql = "UPDATE users SET deleted = true WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
	


}
