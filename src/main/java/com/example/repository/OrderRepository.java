package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

@Repository
public class OrderRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final String TABLE_ORDERS = "orders";
	private static final String TABLE_ORDER_ITEM = "order_items";
	private static final String TABLE_ORDER_TOPPING = "order_toppings";
	private static final String TABLE_ITEM = "items";
	private static final String TABLE_TOPPING = "toppings";
	
	private SimpleJdbcInsert insert;
	
	/**
	 * INSERT時に採番されたIDを取得する方法.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) ->{
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		
		int preId = -1;
		int preOrderItemCheckId = -1;
		
		while(rs.next()) {
			
			int id = rs.getInt("A_id");
			
			if(id != preId) {
				Order order = new Order();
				order.setId(rs.getInt("A_id"));
				order.setUserId(rs.getInt("A_user_id"));
				order.setStatus(rs.getInt("A_status"));
				order.setTotalPrice(rs.getInt("A_total_price"));
				order.setOrderDate(rs.getDate("A_order_date"));
				order.setDestinationName(rs.getString("A_destination_name"));
				order.setDestinationEmail(rs.getString("A_destination_email"));
				order.setDestinationZipcode(rs.getString("A_destination_zipcode"));
				order.setDestinationAddress(rs.getString("A_destination_address"));
				order.setDestinationTel(rs.getString("A_destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("A_delivery_time"));
				order.setPaymentMethod(rs.getInt("A_payment_method"));
				
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}
			
			int orderItemCheckId = rs.getInt("B_id");
			
			if(orderItemCheckId != 0 && orderItemCheckId != preOrderItemCheckId) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("B_id"));
				orderItem.setItemId(rs.getInt("B_item_id"));
				orderItem.setOrderId(rs.getInt("B_order_id"));
				orderItem.setQuantity(rs.getInt("B_quantity"));
				
				String str = rs.getString("B_size");
				char[] toChar = str.toCharArray();
				Character toCharacter = toChar[0];
				orderItem.setSize(toCharacter);
				
				Item item = new Item();
				item.setId(rs.getInt("C_id"));
				item.setName(rs.getString("C_name"));
				item.setDescription(rs.getString("C_description"));
				item.setPriceM(rs.getInt("C_price_m"));
				item.setPriceL(rs.getInt("C_price_l"));
				item.setImagePath(rs.getString("C_image_path"));
				item.setDeleted(rs.getBoolean("C_deleted"));
				orderItem.setItem(item);
				
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
				
			}
			
			int orderToppingCheckId = rs.getInt("D_id");
			
			if(orderToppingCheckId != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("D_id"));
				orderTopping.setToppingId(rs.getInt("D_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("D_order_item_id"));
				
				Topping topping = new Topping();
				topping.setId(rs.getInt("E_id"));
				topping.setName(rs.getString("E_name"));
				topping.setPriceM(rs.getInt("E_price_m"));
				topping.setPriceL(rs.getInt("E_price_l"));
				orderTopping.setTopping(topping);
				
				orderToppingList.add(orderTopping);
			}
			preId = id;
			preOrderItemCheckId = orderItemCheckId;
		}
		return orderList;
		
	};
	
	/**
	 * 5つのテーブルを結合.
	 * @return
	 */
	private String join5Table() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT A.id A_id, A.user_id A_user_id, A.status A_status, A.total_price A_total_price, A.order_date A_order_date, A.destination_name A_destination_name, A.destination_email A_destination_email, A.destination_zipcode A_destination_zipcode, A.destination_address A_destination_address, A.destination_tel A_destination_tel, A.delivery_time A_delivery_time, A.payment_method A_payment_method, ");
		sb.append(" B.id B_id, B.item_id B_item_id, B.order_id B_order_id, B.quantity B_quantity, B.size B_size, ");
		sb.append(" C.id C_id, C.name C_name, C.description C_description, C.price_m C_price_m, C.price_l C_price_l, C.image_path C_image_path, C.deleted C_deleted, ");
		sb.append(" D.id D_id, D.topping_id D_topping_id, D.order_item_id D_order_item_id, ");
		sb.append(" E.id E_id, E.name E_name, E.price_m E_price_m, E.price_l E_price_l");
		sb.append(" FROM " + TABLE_ORDERS + " A LEFT OUTER JOIN " + TABLE_ORDER_ITEM + " B ON A.id = B.order_id ");
		sb.append(" LEFT OUTER JOIN " + TABLE_ITEM + " C ON B.item_id = C.id ");
		sb.append(" LEFT OUTER JOIN " + TABLE_ORDER_TOPPING + " D ON B.id = D.order_item_id ");
		sb.append(" LEFT OUTER JOIN " + TABLE_TOPPING + " E ON E.id = D.topping_id ");
		
	    String JoinSql = sb.toString();
	    return JoinSql;
	}
	
	/**
	 * 商品のオーダーをインサートするリポジトリ.
	 * @param order
	 */
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
        Number key = insert.executeAndReturnKey(param);
		order.setId(key.intValue());
		return order;
	}
	
	/**
	 * 状態とユーザIDを検索するリポジトリ.
	 * @param status
	 * @param userId
	 * @return
	 */
	public List<Order> findByStatusAndUserId(Integer status, Integer userId){
		StringBuilder sql = new StringBuilder();
		sql.append(join5Table());
		sql.append(" WHERE status = :status AND user_id = :userId ");
		SqlParameterSource param = new MapSqlParameterSource().addValue("status", status).addValue("userId", userId);
		List<Order> orderList = template.query(sql.toString(), param, ORDER_RESULT_SET_EXTRACTOR );
		return orderList;
	}
	
	/**
	 * 注文商品を1件検索するリポジトリ.
	 * @param orderId
	 * @return
	 */
	public Order deepLoad(Integer id) {
		StringBuilder sql = new StringBuilder();
		sql.append(join5Table());
		sql.append(" WHERE A.id = :id ");
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Order> orderList = template.query(sql.toString(), param, ORDER_RESULT_SET_EXTRACTOR);
		return orderList.get(0);
	}
	
	private static final RowMapper<Order> ORDER_ROW_MAPPER = (rs,i) -> {
		Order order = new Order();
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationTel(rs.getString("destination_tel"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setId(rs.getInt("id"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setUserId(rs.getInt("user_id"));
		return order;
	};
	
	/**
	 * 注文確定をするために1件検索するリポジトリ.
	 * @param id
	 * @return
	 */
	public Order load(Integer id) {
		String sql = "SELECT id, user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method FROM orders WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Order order = template.queryForObject(sql, param, ORDER_ROW_MAPPER);
		return order;
	}
	
	/**
	 * お客様情報をアップデートするためのリポジトリ.
	 * @param order
	 */
	public void update(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		String sql = "UPDATE orders SET order_date =:orderDate, destination_name =:destinationName, destination_email =:destinationEmail, destination_zipcode =:destinationZipcode, destination_address =:destinationAddress, destination_tel =:destinationTel, delivery_time =:deliveryTime, payment_method =:paymentMethod";
		template.update(sql, param);
	}

}
