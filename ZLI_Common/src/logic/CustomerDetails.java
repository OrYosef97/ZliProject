package logic;

import java.io.Serializable;

public class CustomerDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3818089479591245779L;
	String firstName;
	String lastName;
	Integer customerID;
	Integer creditCard;
	Integer phoneNumber;
	String emailAddress;
	boolean customerStatus;
	
	public CustomerDetails(String firstName,String lastName,Integer customerID,Integer creditCard,Integer phoneNumber, String emailAddress, boolean customerStatus)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerID = customerID;
		this.creditCard = creditCard;
		this.phoneNumber = phoneNumber;
		this.emailAddress=emailAddress;
		this.customerStatus = customerStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public Integer getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(Integer creditCard) {
		this.creditCard = creditCard;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public boolean isCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(boolean customerStatus) {
		this.customerStatus = customerStatus;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return firstName + lastName + customerID + creditCard +	phoneNumber + emailAddress + customerStatus;
	}
	
	
}
