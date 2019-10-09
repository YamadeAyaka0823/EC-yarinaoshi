
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SpringBach {
	
	public static void main(String[] args) throws IOException {
		Connection conn = null;
		
		try {
			Class.forName("org.postgresql.Driver");
			
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ec-201907a", "postgres", "postgres");
			
		    Statement stmt = conn.createStatement();
		
		try {
			File file = new File("C:/env/springworkspace/EC-yarinaoshi/items.csv");
			BufferedReader br = new BufferedReader((new InputStreamReader(new FileInputStream(file), "SJIS")));

			List<String> bachList = new ArrayList<>();
			String str = br.readLine();
			while (str != null) {
			    String[] data = str.split(",",0);
				bachList.add(str);
				str = br.readLine();
				stmt.executeUpdate("INSERT INTO items (name, description, price_m, price_l, image_path, deleted) VALUES ('" + data[0] + "','" + data[1] + "','" + data[2] + "','" + "400" + "','" + data[3] + "','" + data[4] + "')");
			}
			br.close();
			for(String work : bachList) {
				
				System.out.println(work);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		  stmt.close();
		  conn.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}


