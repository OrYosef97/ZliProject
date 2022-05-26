package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gui.ServerScreenController;
import logic.Item;
import logic.Order;
import logic.Product;
import logic.User;
import server.ServerUI;

public class GeneralConnector {
	
	protected static Connection conn;
	//maybe to add a constractor for instance of the class with connect to DB
	@SuppressWarnings("deprecation")
//	public mysqlConnection(String path, String userName, String password) {
//		connectToDB(path,userName,password);
//	}
	

	public static Connection connectToDB(String path, String userName, String password,ServerScreenController ssc) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
			ssc.setTextToConsole("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			System.out.println("Driver definition failed");
			ssc.setTextToConsole("Driver definition failed");
			
		}

		try {
			conn = DriverManager.getConnection(path, userName, password);
			System.out.println("SQL connection succeed");
			ssc.setTextToConsole("SQL connection succeed");
		} catch (SQLException ex) {/* handle any errors */
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			ssc.setTextToConsole("SQLException: " + ex.getMessage());
			ssc.setTextToConsole("SQLState: " + ex.getSQLState());
			ssc.setTextToConsole("VendorError: " + ex.getErrorCode());
		}
		return conn;
	}

	

	public static ArrayList<Product> getProducts() throws  Exception {
		Statement stmt;
		try {
			stmt=conn.createStatement();
			ArrayList<Product> productArray = new ArrayList<Product>();
			ResultSet rs=stmt.executeQuery("SELECT * from products;");
			while(rs.next())
	 		{
				productArray.add(new Product(rs.getString("PID"), rs.getString("name"), rs.getString("type"), rs.getDouble("price"),
						rs.getString("mainColor"), rs.getString("Image"),rs.getString("isSelfMade")));
	 		}
			System.out.println(productArray);
			return productArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
	
	public static ArrayList<Item> getItems() {
		Statement stmt;
		try {
			stmt=conn.createStatement();
			ArrayList<Item> itemsArray = new ArrayList<>();
			ResultSet rs=stmt.executeQuery("SELECT * from items;");
			while(rs.next())
	 		{
				itemsArray .add(new Item(rs.getString("itemID"), rs.getString("name"), rs.getString("type"), rs.getDouble("price"),rs.getString("image")));
	 		}
			System.out.println(itemsArray );
			return itemsArray ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
	
	
	public static ArrayList<Order> getOrders( ) {
		Statement stmt;
		try {
			stmt=conn.createStatement();
			ArrayList<Order> ordersArray = new ArrayList<Order>();
			//StringBuilder orders=new StringBuilder();
			ResultSet rs=stmt.executeQuery("SELECT * from orders;");
			while(rs.next())
	 		{
//				orders.append(rs.getString(1)+ "//z" + rs.getString(2) + "//z" + rs.getString(3) + "//z" + rs.getString(4)+ "//z"+ rs.getString(5)+ "//z"
//						+rs.getString(6)+ "//z"+rs.getString(7)+ "//z"+rs.getString(8)+ "//z");
			ordersArray.add(new Order(rs.getInt("orderID"),rs.getString("customerName"),rs.getString("greeting"),rs.getInt("isSelfMade")
					,rs.getString("orderDetails"), rs.getInt("hasDelivery"),rs.getString("address"),fixDate(rs.getString("deliveryDate")),
					rs.getString("branch"),rs.getString("paymentDetails"),rs.getDouble("price"),fixDate(rs.getString("orderDate")),rs.getString("status")));	
								/*rs.getString("products")*/
	 		}

			System.out.println(ordersArray);
			return ordersArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
	
	public static String fixDate(String str) {
		String date[] = str.split(" ");
		date=date[0].split("-");
		return date[2] + "." + date[1] + "." + date[0];

	}


	//@author Or
	//Get User if exist by name
	public static User getUserInfo(String userName) {
		User user = null;
		PreparedStatement stmt;//
		try {
			stmt = conn.prepareStatement("SELECT * FROM login where userName = ?");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				user=new User(rs.getString("userName"),rs.getString("password"),rs.getString("userType"),rs.getInt("isLoggedIn"),rs.getInt("userID"));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(user);
		return user;
	}
	
	//@author gal
	//update userInfo to logged in
	public static Boolean UpdateLoggedIn(String userName,int Status) {
		PreparedStatement stmt;//
		try {
			stmt = conn.prepareStatement("UPDATE login SET isLoggedIn=? WHERE userName=?;");
			stmt.setInt(1, Status);
			stmt.setString(2, userName);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void CloseConnection() throws SQLException {//maybe gets con as parameter
		// con.close();
		try {
			ServerUI.closeServer();
			//mayby needs to close DB conn
		} catch (Exception e) {
			throw e;
		}

	}
	
	@SuppressWarnings("unused")
	private static boolean StoreProduct(Product product) throws Exception {
		PreparedStatement stmt;//
		try {
			stmt = conn.prepareStatement("INSERT INTO `zliproject`.`products` (`PID`, `name`, `type`, `price`, `mainColor`, `itemsIncluded`, `isSelfMade`)"
					+ "VALUES ('?', '?', '?', '?', '?', '?', '?');");
			stmt.setString(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setString(3,product.getType());
			stmt.setDouble(4, product.getPrice());
			stmt.setString(5, product.getMainColor());
			stmt.setString(6, product.getImageUrl());
			stmt.setInt(8, Integer.parseInt(product.getIsSelfMade()));
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


}

	


