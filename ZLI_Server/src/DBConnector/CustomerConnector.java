package DBConnector;

import java.sql.PreparedStatement;

import logic.Order;

public class CustomerConnector {
	public boolean createOrder(Order order) {
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
}
