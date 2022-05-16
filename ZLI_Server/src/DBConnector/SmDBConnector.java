package DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.CustomerDetails;
import logic.Order;

public class SmDBConnector {

	
	public static ArrayList<CustomerDetails> getCustomerDetails( ) {
		Statement stmt;
		System.out.println("got to db connector");
		try {
			stmt=mysqlConnection.conn.createStatement();
			ArrayList<CustomerDetails> customerDetailsArray = new ArrayList<CustomerDetails>();
			//StringBuilder orders=new StringBuilder();
			ResultSet rs=stmt.executeQuery("SELECT * from customerdetails;");
			while(rs.next())
	 		{
			customerDetailsArray.add(new CustomerDetails(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4),
					rs.getInt(5),rs.getString(6),rs.getBoolean(7)));	
				
	 		}
			System.out.println(customerDetailsArray);
			return customerDetailsArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
}
