package DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import logic.Customer;
import logic.Order;
import logic.User;

public class CustomerDBConnector {
	
	public static Customer getCustomer(User user) {
		Customer customer = null;
		try {
			PreparedStatement stmt;//
			stmt = GeneralConnector.conn.prepareStatement("SELECT * FROM customer where userID=?;");
			stmt.setInt(1, user.getUserId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				customer = new Customer(rs.getInt("userID"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("phonNumber"),
						rs.getString("email"),rs.getString("branch"),rs.getString("creditCardNumber"),rs.getString("accountStatus"));
			}
			return customer;
			
		} catch (Exception e) {
			System.out.println("Can Not get customer orders!");
			return null;
		}
	}
	
	public static boolean createOrder(Order order) {
		try {
			PreparedStatement stmt;//
			stmt = GeneralConnector.conn
					.prepareStatement("INSERT INTO `zliproject`.`orders` (`custumerName`, `greeting`, `isSelfMade`,"
							+ " `orderDetails`, `hasDelivery`, `address`, `deliveryDate`, `branch`, `paymentDetails`, `price`, `orderDate`, `status`) "
							+ "VALUES ('?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?');");
			//stmt.setInt(1, order.getOrderNumber());
			stmt.setString(1,order.getCustomerName());
			stmt.setString(2, order.getGreeting());
			stmt.setInt(3,order.getIsSelfMade());
			stmt.setString(4, order.getOrderDetails());
			stmt.setInt(5, order.getHasDelivery());
			stmt.setString(6, order.getAddress());
			stmt.setString(7, order.getDeliveryDate());//need to change order field date so it will be date value
			stmt.setString(8, order.getBranch());
			stmt.setString(9, order.getPaymentDetails());
			stmt.setDouble(10, order.getPrice());
			stmt.setString(11, order.getOrderDate());//need to change order field date so it will be date value
			stmt.setString(12, order.getStatus());
			stmt.executeUpdate();
			return true;
			
		} catch (Exception e) {
			System.out.println("Can Not save order!");
			return false;
		}
	}
	public static ArrayList<Order> getCustomerOrders(User user) {
		try {
			 ArrayList<Order> ordersArray=new ArrayList<Order>();
			PreparedStatement stmt;//
			stmt = GeneralConnector.conn.prepareStatement("SELECT * FROM orders where userID=?;");
			stmt.setInt(1, user.getUserId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				ordersArray.add(new Order(rs.getInt("orderID"), rs.getString("customerName"), rs.getString("greeting"),
						rs.getInt("isSelfMade"),rs.getString("orderDetails") , rs.getInt("hasDelivery"),
						rs.getString("address"), rs.getString("deliveryDate"), rs.getString("branch"), rs.getString("paymentDetails"),
						rs.getDouble("price"), rs.getString("orderDate"), rs.getString("status")));
			}
			return ordersArray;
			
		} catch (Exception e) {
			System.out.println("Can Not get customer orders!");
			return null;
		}
	}
	
	
}
