package dessertmarket.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DessertStorage {
	ArrayList<Dessert> dessertList = new ArrayList<>();
	final int MAX_QUANTITY = 10;
	private String dessertFilename = "C:\\Users\\kbj\\eclipse-workspace\\DessertMarket\\DessertMarket\\dessertlist.txt";
	private int lastId;
	private boolean isSaved;
	
	public DessertStorage() throws IOException {
		loadDessertListFromFile();
		generateLastId();
		isSaved = true;
	}
	
	private void generateLastId() {
		lastId = 0;
		for (Dessert dessert : dessertList) {
			int id = dessert.getDessertId();
			if (id > lastId) lastId = id;
		}
	}

	private void loadDessertListFromFile() throws IOException {
		FileReader fr;
		try {
			fr = new FileReader(dessertFilename);
			BufferedReader br = new BufferedReader(fr);
			String idStr;
			while ((idStr = br.readLine()) != null && !idStr.equals("")) {
				int id = Integer.parseInt(idStr);
				String title = br.readLine();
				String author = br.readLine();
//				String publisher = br.readLine();
				int price = Integer.parseInt(br.readLine());
				dessertList.add(new Dessert(id, title, author, /* publisher, */price));
			}
			fr.close();
			br.close();

		} catch (FileNotFoundException |  NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getNumDesserts() {
		return dessertList.size();
	}

	public String getDessertInfo(int i) {
		return dessertList.get(i).toString();
	}

	public boolean isValidDessert(int dessertId) {
		for (Dessert dessert : dessertList) {
			if (dessert.getDessertId() == dessertId) return true;
		}
		return false;
	}

	public Dessert getDessertById(int dessertId) {
		for (Dessert dessert : dessertList) {
			if (dessert.getDessertId() == dessertId)
				return dessert;
		}
		return null;
	}

	public int getMaxQuantity() {
		return MAX_QUANTITY;
	}

	public boolean isEmpty() {
		return dessertList.size() == 0;
	}

	public void deleteItem(int dessertId) {
		dessertList.remove(getDessertById(dessertId));
		isSaved = false;
	}

	public void addDessert(String title, String author, /*String publisher*/ int price) {
		
		Dessert dessert = new Dessert(++lastId, title, author, /* publisher, */price);
		dessertList.add(dessert);
		isSaved = false;
	}

	public boolean isSaved() {
		return isSaved;
	}

	public void saveDessertList2File() {

		try {
			FileWriter fw = new FileWriter(dessertFilename);
			for (Dessert dessert : dessertList) {
				fw.write(dessert.getDessertId() + "\n");
				fw.write(dessert.getTitle() + "\n");
				fw.write(dessert.getAuthor() + "\n");
				//fw.write(dessert.getPublisher() + "\n");
				fw.write(dessert.getPrice() + "\n");
			}
			fw.close();
			isSaved = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
