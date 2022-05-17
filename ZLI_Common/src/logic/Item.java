package logic;

import javafx.scene.image.Image;

public class Item {
	String id;
	String name;
	String type;
	Double price;
	Image image;
	//constructor with all fields
	public Item(String id, String name, String type, Double price, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.image = image;
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
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	

}
