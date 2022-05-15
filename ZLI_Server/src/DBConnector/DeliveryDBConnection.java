package DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gui.ServerScreenController;


public class DeliveryDBConnection {
	private static Connection conn;
	
	private static mysqlConnection sqlConnection = null;
	
	//maybe to add a constractor for instance of the class with connect to DB
	@SuppressWarnings("deprecation")
//	public mysqlConnection(String path, String userName, String password) {
//		connectToDB(path,userName,password);
//	}

	
	public static Boolean UpdateOrderDelivered(String OrderNumber) {
		PreparedStatement stmt;//
		try {
			stmt = mysqlConnection.conn.prepareStatement("DELETE FROM orders WHERE orderID = ?");
			stmt.setString(1, OrderNumber);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
