package dessertmarket.model;

public class CartItem {
	Dessert dessert;
	int dessertId;
	int quantity;
	
	public CartItem(Dessert dessert) {
		this.dessert = dessert;
		this.dessertId = dessert.getDessertId();
		this.quantity = 1;
	}
	
	public Dessert getBook() {
		return dessert;
	}
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int quantity) {
		this.quantity += quantity;
		
	}

	@Override
	public String toString() {
		return dessert.getDessertId() + ", " + dessert.getTitle() + ", " + quantity + "개, " + getPrice() + "원";
	}

	public int getPrice() {
		return quantity * dessert.getPrice();
	}

	public Dessert getDessert() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
