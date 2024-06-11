package dessertmarket.model;

import java.util.ArrayList;

public class Cart {
	//private CartItem[] itemList = new CartItem[64];
	private ArrayList<CartItem> itemList = new ArrayList<>();
	//private int numItems = 0;

	public boolean isEmpty() {
		return itemList.isEmpty();
	}

	public ArrayList<CartItem> getItemList() {
		return itemList;
	}

	public int getNumItems() {
		return itemList.size();
	}

	public String getItemInfo(int index) {
		return itemList.get(index).toString();
	}

	public void addItem(Dessert dessert) {
		
		CartItem item = getCartItem(dessert);
		if (item == null) {
			itemList.add(new CartItem(dessert));
		}
		else {
			item.addQuantity(1);
		}
	}
	
	private CartItem getCartItem(Dessert dessert) {
		
		for (CartItem item : itemList) {
			if (item.getDessert() == dessert) return item;
		}
		
		return null;
	}
	
	private CartItem getCartItem(int dessertId) {
		for (CartItem item : itemList) {
			if (item.dessertId == dessertId) return item;
		}
		return null;
	}
	

	public void resetCart() {
		itemList.clear();
	}

	public boolean isValidItem(int dessertId) {
		for (CartItem item : itemList) {
			if (item.dessertId == dessertId) return true;
		}
		return false;
	}

	public void deleteItem(int dessertId) {
		CartItem item = getCartItem(dessertId);
		itemList.remove(item);
	}

	public void updateQuantity(int dessertId, int quantity) {
		
		if (quantity == 0)
			deleteItem(dessertId);
		else {
			CartItem item = getCartItem(dessertId);
			item.setQuantity(quantity);
		}
		
	}

	public int getTotalPrice() {
		int totalPrice = 0;
		for (CartItem item : itemList) {
			totalPrice += item.getPrice();
		}
		return totalPrice;
	}

	
	
}


