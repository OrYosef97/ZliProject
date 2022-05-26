package logic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private Integer orderNumber;
	private String customerName;
	private String greeting;
	private Integer isSelfMade;
	private String orderDetails;
	private Integer hasDelivery;
	private String address;
	private String deliveryDate;
	private String branch;
	private ArrayList<Product> products;
	private String paymentDetails;
	private Double price;
	private String orderDate;
	private String status;

	


	
	public Order(Integer orderNumber, String customerName, String greeting, Integer isSelfMade, String orderDetails, Integer hasDelivery,
			String address, String deliveryDate, String branch, String paymentDetails,
			Double price,String orderDate, String status) {
		super();
		this.orderNumber = orderNumber;
		this.customerName = customerName;
		this.greeting = greeting;
		this.isSelfMade = isSelfMade;
		this.hasDelivery = hasDelivery;
		this.address = address;
		this.deliveryDate = deliveryDate;
		this.branch = branch;
		this.paymentDetails = paymentDetails;
		this.price = price;
		this.orderDate = orderDate;
		this.status = status;
	}

	
	
	public String getOrderDate() {
		return orderDate;
	}



	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}



	public String getOrderDetails() {
		return orderDetails;
	}





	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}




	public String getGreeting() {
		return greeting;
	}





	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}





	public Integer getIsSelfMade() {
		return isSelfMade;
	}





	public void setIsSelfMade(Integer isSelfMade) {
		this.isSelfMade = isSelfMade;
	}





	public Integer getHasDelivery() {
		return hasDelivery;
	}





	public void setHasDelivery(Integer hasDelivery) {
		this.hasDelivery = hasDelivery;
	}





	public String getAddress() {
		return address;
	}





	public void setAddress(String address) {
		this.address = address;
	}





	public String getDeliveryDate() {
		return deliveryDate;
	}





	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}





	public String getBranch() {
		return branch;
	}





	public void setBranch(String branch) {
		this.branch = branch;
	}





	public ArrayList<Product> getProducts() {
		return products;
	}





	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}





	public String getPaymentDetails() {
		return paymentDetails;
	}





	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}





	public Double getPrice() {
		return price;
	}





	public void setPrice(Double price) {
		this.price = price;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String DeliverytoString() {

		return orderNumber + "//z" + price + "//z" + branch
				+ "//z" + deliveryDate + "//z";
	}
	
	@Override
	public boolean equals(Object obj) {
		Order another = (Order)obj;
		return this.orderNumber==another.getOrderNumber();
	}





	public Integer getOrderNumber() {
		return orderNumber;
	}





	public String getCustomerName() {

		return customerName;

	}

}
