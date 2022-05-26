package DBConnector;

import java.io.BufferedInputStream;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.Converter;
import logic.Survey;

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

	public static ArrayList<Survey> LoadSurveys() {
		Statement stmt;
		try {
			stmt=GeneralConnector.conn.createStatement();
			ArrayList<Survey> surveyArray = new ArrayList<Survey>();
			ResultSet rs=stmt.executeQuery("SELECT * from survey;");
			while(rs.next())
	 		{
//				orders.append(rs.getString(1)+ "//z" + rs.getString(2) + "//z" + rs.getString(3) + "//z" + rs.getString(4)+ "//z"+ rs.getString(5)+ "//z"
//						+rs.getString(6)+ "//z"+rs.getString(7)+ "//z"+rs.getString(8)+ "//z");
			surveyArray.add(new Survey(rs.getInt("surveyID"),rs.getString("surveyType"),rs.getString("surveyUpdater")));	
								/*rs.getString("products")*/
	 		}

			System.out.println(surveyArray);
			return surveyArray;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;			
		}
	}
	public static Boolean AddConclusions(Survey survey,File conclusionFile) throws Exception { //needs to be finished after getting blob
		PreparedStatement stmt;//
		System.out.println(conclusionFile.getName());
		Integer surveyID = survey.getSurveyID();
		BufferedInputStream file = Converter.objectToByteArrayIS(conclusionFile);
		try {
			stmt = GeneralConnector.conn.prepareStatement("UPDATE survey_results SET conclusions = ? WHERE surveyID = ? ;");
			stmt.setInt(2, surveyID);	
			stmt.setBlob(1, file);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
}

