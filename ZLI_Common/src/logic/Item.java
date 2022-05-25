package logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Item extends GeneralItem implements Serializable {
	
	public Item(String id, String name, String type, Double price, String imageUrl, Integer amount) {
		super(id, name, type, price, imageUrl,amount);
		
	}
	public Item(String id, String name, String type, Double price, String imageUrl) {
		super(id, name, type, price, imageUrl);
	}
}
