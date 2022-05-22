package logic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Product implements Serializable {
	private Integer id;
	private String name;
	private String type;
	private Double price;
	private String mainColor;
	private String imageUrl;
	private ArrayList<Item> itemsIncluded = null;
	private String isSelfMade;
	
	public Product(Integer id, String name, String type, Double price, String mainColor,String imageUrl, ArrayList<Item> itemsIncluded,
			String isSelfMade) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.mainColor = mainColor;
		this.itemsIncluded = itemsIncluded;
		this.isSelfMade = isSelfMade;
		this.imageUrl=imageUrl.isEmpty()?"/images.img/defaultImage.png":imageUrl;
	}

	public String getIsSelfMade() {
		return isSelfMade;
	}

	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
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
	public String getMainColor() {
		return mainColor;
	}
	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}
	public String getImage() {
		return imageUrl;
	}
	public void setImage(String image) {
		this.imageUrl = image;
	}
	public ArrayList<Item> getItemsIncluded() {
		return itemsIncluded;
	}

	public void setItemsIncluded(ArrayList<Item> itemsIncluded) {
		this.itemsIncluded = itemsIncluded;
	}
	
	@Override
	public boolean equals(Object obj) {
		Product another = (Product)obj;
		return this.id==another.getId();
	}
	
		
	
}
