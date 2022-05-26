package logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Survey implements Serializable {
	private Integer surveyID;
	private String surveyType;
	private String surveyUpdater;
	private Integer customerID, ans1, ans2, ans3, ans4, ans5, ans6;
	
	public Survey(Integer surveyID, String surveyType, String surveyUpdater) {
		super();
		this.surveyID = surveyID;
		this.surveyType = surveyType;
		this.surveyUpdater = surveyUpdater;
	}
	public Survey(Integer surveyID, String surveyType, String surveyUpdater, Integer customerID, Integer ans1,
			Integer ans2, Integer ans3, Integer ans4, Integer ans5, Integer ans6) {
		super();
		this.surveyID = surveyID;
		this.surveyType = surveyType;
		this.surveyUpdater = surveyUpdater;
		this.customerID = customerID;
		this.ans1 = ans1;
		this.ans2 = ans2;
		this.ans3 = ans3;
		this.ans4 = ans4;
		this.ans5 = ans5;
		this.ans6 = ans6;
	}
	public Integer getSurveyID() {
		return surveyID;
	}
	public void setSurveyID(Integer surveyID) {
		this.surveyID = surveyID;
	}
	public String getSurveyType() {
		return surveyType;
	}
	public void setSurveyType(String surveyType) {
		this.surveyType = surveyType;
	}
	public String getSurveyUpdater() {
		return surveyUpdater;
	}
	public void setSurveyUpdater(String surveyUpdater) {
		this.surveyUpdater = surveyUpdater;
	}
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getAns1() {
		return ans1;
	}
	public void setAns1(Integer ans1) {
		this.ans1 = ans1;
	}
	public Integer getAns2() {
		return ans2;
	}
	public void setAns2(Integer ans2) {
		this.ans2 = ans2;
	}
	public Integer getAns3() {
		return ans3;
	}
	public void setAns3(Integer ans3) {
		this.ans3 = ans3;
	}
	public Integer getAns4() {
		return ans4;
	}
	public void setAns4(Integer ans4) {
		this.ans4 = ans4;
	}
	public Integer getAns5() {
		return ans5;
	}
	public void setAns5(Integer ans5) {
		this.ans5 = ans5;
	}
	public Integer getAns6() {
		return ans6;
	}
	public void setAns6(Integer ans6) {
		this.ans6 = ans6;
	}
	
	
	
}
