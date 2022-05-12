package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gui.ServerScreenController;
import logic.Order;

public class mysqlConnection {
	
	private static Connection conn;
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

	public static Order getOrder(String orderNum) {
		Order order = null;
		PreparedStatement stmt;//
		try {
			stmt = conn.prepareStatement("SELECT * FROM orders where orderNumber = ?");
			stmt.setString(1, orderNum);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				order = new Order(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getString(8));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(order);
		return order;
	}
	
	
	
	public static Boolean updateColor(String color, String orderNum) {//maybe with order num
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("UPDATE orders SET color=? WHERE orderNumber=?; ");
			stmt.setString(1, color);
			stmt.setString(2, orderNum);
			stmt.executeUpdate();
			return true;//change to relevant
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Boolean updateDate(String date,String orderNum) {
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement("UPDATE orders SET date=? WHERE orderNumber=?; ");
			stmt.setString(1, date);
			stmt.setString(2, orderNum);
			stmt.executeUpdate();
			return true;//change to relevant
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
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
			ordersArray.add(new Order(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6),fixDate(rs.getString(7)),fixDate(rs.getString(8))));	
				
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


	public static void CloseConnection() throws SQLException {//maybe gets con as parameter
		// con.close();
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			throw e;
		}

	}
	
	public static String getUserInfo(String userName) {
		StringBuilder userInfo = new StringBuilder();
		PreparedStatement stmt;//
		try {
			stmt = conn.prepareStatement("SELECT * FROM login where userName = ?");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				userInfo.append(rs.getString(1)+"//z"+rs.getString(2)+"//z"+rs.getString(3)+"//z"+rs.getString(4));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(userInfo);
		return userInfo.toString();
	}

}
