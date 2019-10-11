import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
			
			try {
				FileWriter f = new FileWriter("C://env/order_20191010.csv", false);
				PrintWriter p = new PrintWriter(new BufferedWriter(f));
				
				p.print("id");
				p.print(",");
				p.print("user_id");
				p.print(",");
				p.print("status");
				p.print(",");
				p.print("total_price");
				p.print(",");
				p.print("order_date");
				p.print(",");
				p.print("destination_name");
				p.print(",");
				p.print("destination_email");
				p.print(",");
				p.print("destination_zipcode");
				p.print(",");
				p.print("destination_address");
				p.print(",");
				p.print("destination_tel");
				p.print(",");
				p.print("delivery_time");
				p.print(",");
				p.print("payment_method");
				p.println();
				
				for(int i = 0; i < orderList.size(); i++) {
					p.print(orderList.get(i));
					p.println();
				}
				p.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
//	public static void exportCsv(Order orderList) {
//		try {
//			FileWriter f = new FileWriter("C://env/order_20191010.csv", false);
//			PrintWriter p = new PrintWriter(new BufferedWriter(f));
//			
//			p.print("id");
//			p.print(",");
//			p.print("user_id");
//			p.print(",");
//			p.print("status");
//			p.print(",");
//			p.print("total_price");
//			p.print(",");
//			p.print("order_date");
//			p.print(",");
//			p.print("destination_name");
//			p.print(",");
//			p.print("destination_email");
//			p.print(",");
//			p.print("destination_zipcode");
//			p.print(",");
//			p.print("destination_address");
//			p.print(",");
//			p.print("destination_tel");
//			p.print(",");
//			p.print("delivery_time");
//			p.print(",");
//			p.print("payment_method");
//			p.println();
//			
//			for(int i = 0; i < orderList.length; i++) {
//				p.print(orderList[i]);
//				p.println();
//			}
//			p.close();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//	}

}
