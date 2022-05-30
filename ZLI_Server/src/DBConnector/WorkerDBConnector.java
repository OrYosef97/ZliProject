package DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/*
 * @author Gal
 * This class control the Delivery Guy data base connection 
 */
public class WorkerDBConnector {

	public static Boolean UpdateSurveyComment(Integer surveyID, String surveyType, String userName,
			Integer customerID,Integer ans1,Integer ans2,Integer ans3,Integer ans4,Integer ans5,Integer ans6) {
		PreparedStatement stmt;//
		try {
			stmt = GeneralConnector.conn.prepareStatement("INSERT INTO survey"
					+ "(surveyID,surveyType, surveyUpdater,customerID,ans1,ans2,ans3,ans4,ans5,ans6)"
					+ "values (?,?,?,?,?,?,?,?,?,?);");
			stmt.setInt(1, surveyID);
			stmt.setString(2, surveyType);
			stmt.setString(3, userName);
			stmt.setInt(4, customerID);
			stmt.setInt(5, ans1);
			stmt.setInt(6, ans2);
			stmt.setInt(7, ans3);
			stmt.setInt(8, ans4);
			stmt.setInt(9, ans5);
			stmt.setInt(10,ans6);
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean CheckOrCreateSurveyResults(Integer surveyID) {
		
		PreparedStatement stmt;//
		try {
			/*check if the survey exist*/
			stmt = GeneralConnector.conn.prepareStatement("select surveyID from survey_results");
			ResultSet rs = stmt.executeQuery();
			if(!rs.next()) { //empty table
			/*add new survey result*/
			stmt = GeneralConnector.conn.prepareStatement("INSERT INTO survey_results "
					+ "(surveyID,surveyDate,participantsNo,surveyType,conclusions)"
					+"VALUES (?,2022-2-2,0,0,null) ");
			stmt.setInt(1, surveyID);
			stmt.execute();
			}
			else{
				rs.beforeFirst();
			 while (rs.next()) {
				 Integer ID = rs.getInt(1);
				 if(ID.equals(surveyID)) return true;//found the survey
			 }
			 /*not found. create a new survey result*/
			 String Date ="2022-02-02";
			 stmt = GeneralConnector.conn.prepareStatement("INSERT INTO survey_results "
						+ "(surveyID,surveyDate,participantsNo,surveyType,conclusions)"
						+"VALUES (?,?,0,0,null) ");
				stmt.setInt(1, surveyID);
				stmt.setString(2, Date);
				stmt.execute();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
}
