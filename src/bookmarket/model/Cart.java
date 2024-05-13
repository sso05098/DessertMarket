package bookmarket.model;

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

	public void addItem(Book book) {
		
		CartItem item = getCartItem(book);
		if (item == null) {
			itemList.add(new CartItem(book));
		}
		else {
			item.addQuantity(1);
		}
	}
	
	private CartItem getCartItem(Book book) {
		
		for (CartItem item : itemList) {
			if (item.getBook() == book) return item;
		}
		
		return null;
	}

	public void resetCart() {
		itemList.clear();
	}	
	
}


