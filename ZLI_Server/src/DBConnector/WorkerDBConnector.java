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

}
