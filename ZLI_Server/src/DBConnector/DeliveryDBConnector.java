package DBConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * @author Gal
 * This class control the Delivery Guy data base connection 
 */
public class DeliveryDBConnector { 

	
	public static Boolean UpdateOrderDelivered(String OrderNumber) {
		PreparedStatement stmt;//
		try {
			stmt = GeneralConnector.conn.prepareStatement("DELETE FROM orders WHERE orderID = ?");
			stmt.setString(1, OrderNumber);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
