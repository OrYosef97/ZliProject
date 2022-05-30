package DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.Order;
import logic.OrderDelivery;
import logic.Survey;

/*
 * @author Gal
 * This class control the Delivery Guy data base connection 
 */
public class DeliveryDBConnector { 

	
	public static Boolean UpdateOrderDelivered(String OrderNumber) {
		PreparedStatement stmt;//
		try {
			stmt = GeneralConnector.conn.prepareStatement("UPDATE orders SET status = ? WHERE orderID = ?;");
			stmt.setString(2, OrderNumber);
			stmt.setString(1, "Delivered");
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static ArrayList<OrderDelivery> GetClientsOrdersDelivery() {
		Statement stmt;
		StringBuilder FullName = new StringBuilder();
		String s;
		try {
			stmt=GeneralConnector.conn.createStatement();
			ArrayList<OrderDelivery> ordersArray = new ArrayList<OrderDelivery>();
			//StringBuilder orders=new StringBuilder();
			ResultSet rs=stmt.executeQuery("SELECT * from orders;");
			/*get from orders*/
			while(rs.next())
	 		{
			ordersArray.add(new OrderDelivery(rs.getInt("orderID"),rs.getInt("userID"),rs.getString("greeting"),rs.getInt("isSelfMade")
					,rs.getString("orderDetails"), rs.getInt("hasDelivery"),rs.getString("address"),rs.getString("deliveryDate"),
					rs.getString("branch"),rs.getString("paymentDetails"),rs.getDouble("price"),rs.getString("status"),rs.getString("orderDate")));	
								/*rs.getString("products")*/
	 		}
			for (OrderDelivery o: ordersArray) {
				PreparedStatement stmt2;
				stmt2=GeneralConnector.conn.prepareStatement("SELECT * FROM customer WHERE userID = ?;");
				stmt2.setInt(1,o.getUserID());
				ResultSet rs2=stmt2.executeQuery();
				if (rs2.next()) {
					s = rs2.getString("firstName");
					o.setPhoneNumber(rs2.getString("phonNumber"));
					FullName.append(s);
					FullName.append(" ");
					FullName.append(rs2.getString("lastName"));
					o.setCustomerName(FullName.toString());
				}
			}
			
			System.out.println(ordersArray);
			return ordersArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
}

