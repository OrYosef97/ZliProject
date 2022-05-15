package logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private String orderNumber;
	private Integer hasGreeting;
	private String greeting;
	private Integer price;
	private Integer isSelfMade;
	private Integer hasDelivery;
	private String items;
	private String branch;
	private String paymentMethod;
	private String deliveryDate;

	

	public Order(String orderNumber, Integer hasGreeting, String greeting, Integer isSelfMade,
			Integer hasDelivery, String deliveryDate,  String branch,String items, String paymentMethod,Integer price) {
		super();
		this.orderNumber = orderNumber;
		this.hasGreeting = hasGreeting;
		this.greeting = greeting;
		this.price = price;
		this.isSelfMade = isSelfMade;
		this.hasDelivery = hasDelivery;
		this.items = items;
		this.branch = branch;
		this.paymentMethod = paymentMethod;
		this.deliveryDate = deliveryDate;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getPrice() {
		return price.intValue();
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	

	public String getdOrder() {
		return deliveryDate;
	}

	public void setdOrder(String dOrder) {
		this.deliveryDate = dOrder;
	}

	public String getShop() {
		return branch;
	}

	public void setShop(String shop) {
		this.branch = shop;
	}

	
	public String getOrderDate() {
		return deliveryDate;
	}

	public void setOrderDate(String orderDate) {
		this.deliveryDate = orderDate;
	}

	
	public String DeliverytoString() {

		return orderNumber + "//z" + price + "//z" + branch
				+ "//z" + deliveryDate + "//z";
	}

}
