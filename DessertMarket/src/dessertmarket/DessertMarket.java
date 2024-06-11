package dessertmarket;

import java.io.IOException;

import dessertmarket.controller.DessertMarketController;
import dessertmarket.model.DessertStorage;
import dessertmarket.model.Cart;
import dessertmarket.view.ConsoleView;

public class DessertMarket {

	public static void main(String[] args) throws IOException {
		// model 생성
		DessertStorage dessertStorage = new DessertStorage();
		Cart cart = new Cart();
		
		// view 생성
		ConsoleView view = new ConsoleView();
		
		// controller 생성 (model, view)
		DessertMarketController controller = new DessertMarketController(dessertStorage, cart, view);
		controller.start();
		
		
	}

}
