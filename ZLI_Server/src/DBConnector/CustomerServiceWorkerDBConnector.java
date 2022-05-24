package DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * @author Gal
 * This class control the Delivery Guy data base connection 
 */
public class CustomerServiceWorkerDBConnector {

	public static Boolean UpdateComplaint(Integer CustomerID,String Date,String Text) {
		PreparedStatement stmt;//
		try {
			stmt = GeneralConnector.conn.prepareStatement("select complaintID from complaints;");
			ResultSet rs = stmt.executeQuery();
			rs.last();
			int num = rs.getInt("complaintID"); //get the last complaintID
			num++;
			String ExpDate = Date;
			Integer ComplaintID=num,Refund =0;
			String Status = "Pending";
			stmt = GeneralConnector.conn.prepareStatement("INSERT INTO complaints"
					+ "(ComplaintID,CustomerID, startingDate,endDate,status,text,refund)"
					+ "values (?,?,?,?,?,?,?);");
			stmt.setInt(1, ComplaintID);
			stmt.setInt(2, CustomerID);
			stmt.setString(3, Date);
			stmt.setString(4, ExpDate);
			stmt.setString(5, Status);
			stmt.setString(6, Text);
			stmt.setInt(7, Refund);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

