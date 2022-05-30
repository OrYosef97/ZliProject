package logic;

import java.io.Serializable;

public class OrderDelivery extends Order implements Serializable{
		private String phoneNumber,customerName;
		
	public OrderDelivery(Integer orderNumber, Integer userID, String greeting, Integer isSelfMade, String orderDetails,
			Integer hasDelivery, String address, String deliveryDate, String branch, String paymentDetails,
			Double price, String orderDate, String status) {
		super(orderNumber, userID, greeting, isSelfMade, orderDetails, hasDelivery, address, deliveryDate, branch,
				paymentDetails, price, orderDate, status);
		// TODO Auto-generated constructor stub
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
}
