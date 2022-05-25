package logic;

import java.io.Serializable;

/*
 * Author: Or
 * General class that contain all relevant fields  about products in order     
 */

@SuppressWarnings("serial")
public class GeneralItem implements Serializable {
	private String id;
	private String name;
	private String type;
	private Double price;
	private String imageUrl;
	private	Integer amount=0;
	
	
	
	public GeneralItem(String id, String name, String type, Double price, String imageUrl,Integer amount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.imageUrl = imageUrl;
		this.amount =amount;
	}
	
	
	
	public GeneralItem(String id, String name, String type, Double price, String imageUrl) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.imageUrl = imageUrl;
		this.amount = 0;
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



	public Integer getAmount() {
		return amount;
	}



	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	

	@Override
	public boolean equals(Object obj) {
		GeneralItem another = (GeneralItem)obj;
		return this.getId().equals(another.getId());
	}

}
