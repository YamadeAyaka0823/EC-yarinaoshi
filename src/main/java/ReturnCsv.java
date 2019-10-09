import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.domain.Order;

public class ReturnCsv {

	public static void main(String[] args) {
		Connection conn = null;
		
		String sql = "SELECT o.id, o.user_id, o.status, o.total_price, o.order_date, o.destination_name, o.destination_email, o.destination_zipcode, o.destination_address, o.destination_tel, o.delivery_time, o.payment_method FROM orders o INNER JOIN users u ON o.user_id = u.id ";
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ec-201907a", "postgres", "postgres");
			
			Statement stmt = conn.createStatement();
			
			ResultSet result = stmt.executeQuery(sql);
			
			List<Order> orderList = new ArrayList<>();
			
			while(result.next()) {
				
				Order order = new Order();
				
				order.setId(result.getInt("id"));
				order.setUserId(result.getInt("user_id"));
				order.setStatus(result.getInt("status"));
				order.setTotalPrice(result.getInt("total_price"));
				order.setOrderDate(result.getDate("order_date"));
				order.setDestinationName(result.getString("destination_name"));
				order.setDestinationEmail(result.getString("destination_email"));
				order.setDestinationZipcode(result.getString("destination_zipcode"));
				order.setDestinationAddress(result.getString("destination_address"));
				order.setDestinationTel(result.getString("destination_tel"));
				order.setDeliveryTime(result.getTimestamp("delivery_time"));
				order.setPaymentMethod(result.getInt("payment_method"));
				
				orderList.add(order);
				
			}
			System.out.println(orderList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
