package logic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private String orderNumber;
	private String custumerName;
	private String greeting;
	private Integer isSelfMade;
	private String orderDetails;
	private Integer hasDelivery;
	private String address;
	private String deliveryDate;
	private String branch;
	private ArrayList<Product> products;
	private String paymentDetails;
	private Integer price;
	private String status;

	


	
	public Order(String orderNumber, String custumerName, String greeting, Integer isSelfMade, String orderDetails, Integer hasDelivery,
			String address, String deliveryDate, String branch, ArrayList<Product> products, String paymentDetails,
			Integer price, String status) {
		super();
		this.orderNumber = orderNumber;
		this.custumerName = custumerName;
		this.greeting = greeting;
		this.isSelfMade = isSelfMade;
		this.hasDelivery = hasDelivery;
		this.address = address;
		this.deliveryDate = deliveryDate;
		this.branch = branch;
		this.products = products;
		this.paymentDetails = paymentDetails;
		this.price = price;
		this.status = status;
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





	public Integer getPrice() {
		return price;
	}





	public void setPrice(Integer price) {
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





	public String getOrderNumber() {
		return orderNumber;
	}





	public String getCustumerName() {
		return custumerName;
	}

}
