package dessertmarket.model;

public class Dessert {
	private int dessertId;
	private String title;
	private String author;
	//private String publisher;
	private int price;
	
	public Dessert(int dessertId, String title, String author, /* String publisher, */int price) {
		this.dessertId = dessertId;
		this.title = title;
		this.author = author;
		//this.publisher = publisher;
		this.price = price;
	}
	
	public int getDessertId() {
		return dessertId;
	}
	public String getTitle() {
		return title;
	}
	public String getAuthor() {
		return author;
	}
	/* public String getPublisher() {
		return publisher;
	} */
	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return dessertId + ", " + title + ", " + author + ", " + /* publisher
				+ ", " + */ price + "원";
	}
}
