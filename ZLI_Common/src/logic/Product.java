package logic;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Product extends GeneralItem implements Serializable  {
	private String mainColor;
	private ArrayList<GeneralItem> itemsIncluded = null;
	private String isSelfMade;
	
	public Product(String id, String name, String type, Double price, String imageUrl, String mainColor, String isSelfMade) {
		super(id, name, type, price, imageUrl,0);//setting amount to 0 when creating the object
		this.mainColor =mainColor;
		this.isSelfMade =isSelfMade;
	}


	
	
	public String getMainColor() {
		return mainColor;
	}




	public void setMainColor(String mainColor) {
		this.mainColor = mainColor;
	}




	public ArrayList<GeneralItem> getItemsIncluded() {
		return itemsIncluded;
	}




	public void setItemsIncluded(ArrayList<GeneralItem> itemsIncluded) {
		this.itemsIncluded = itemsIncluded;
	}




	public String getIsSelfMade() {
		return isSelfMade;
	}




	public void setIsSelfMade(String isSelfMade) {
		this.isSelfMade = isSelfMade;
	}




	@Override
	public boolean equals(Object obj) {
		Product another = (Product)obj;
		return this.getId().equals(another.getId());
	}
	
		
	
}
