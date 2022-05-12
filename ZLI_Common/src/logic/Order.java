package logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Order implements Serializable {
	private String orderNumber;
	private Integer price;
	private String greetingCard;
	private String color;
	private String dOrder;
	private String shop;
	private String date;
	private String orderDate;

	public Order(String orderNumber, int price, String greetingCard, String color, String dOrder, String shop,
			String date, String orderDate) {
		super();
		this.orderNumber = orderNumber;
		this.price = price;
		this.greetingCard = greetingCard;
		this.color = color;
		this.dOrder = dOrder;
		this.shop = shop;
		this.date = date;
		this.orderDate = orderDate;
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

	public String getGreetingCard() {
		return greetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		this.greetingCard = greetingCard;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getdOrder() {
		return dOrder;
	}

	public void setdOrder(String dOrder) {
		this.dOrder = dOrder;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {

		return orderNumber + "//z" + price + "//z" + greetingCard + "//z" + color + "//z" + dOrder + "//z" + shop
				+ "//z" + date + "//z" + orderDate;
	}

}
