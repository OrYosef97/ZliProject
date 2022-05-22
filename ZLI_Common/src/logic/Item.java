package logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item implements Serializable {
	private String id;
	private String name;
	private String type;
	private Double price;
	private String imageUrl;
	private	Integer amount=0;
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	//constructor with all fields
	public Item(String id, String name, String type, Double price, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.imageUrl = imageUrl;;
	}
	//constructor without image field
	public Item(String id, String name, String type, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public void addOneItem(){
		amount++;
	}
	public void removeOneItem(){
		if(amount>0)amount--;
	}
	
	@Override
	public boolean equals(Object obj) {
		Item another = (Item)obj;
		return this.id==another.getId();
	}

}
