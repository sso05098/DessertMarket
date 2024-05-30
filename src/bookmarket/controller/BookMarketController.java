package bookmarket.controller;

import bookmarket.model.Admin;
import bookmarket.model.BookStorage;
import bookmarket.model.Cart;
import bookmarket.model.Customer;
import bookmarket.view.ConsoleView;

public class BookMarketController {

	ConsoleView view;
	BookStorage mBookStorage;
	Cart mCart;
	Customer mCustomer;
	Admin mAdmin;
	
	String[] menuList = {
			"0. 종료",
			"1. 도서 정보 보기",
			"2. 장바구니 보기",
			"3. 장바구니에 도서 추가",
			"4. 장바구니 도서 삭제",
			"5. 장바구니 도서 수량 변경",
			"6. 장바구니 비우기",
			"7. 주문",
			"8. 관리자 모드"
	};
	
	String[] adminMenuList = {
			"0. 관리자 모드 종료",
			"1. 도서 추가 하기",
			"2. 도서 삭제 하기",
			"3. 도서 정보 저장하기"
	};
	
	public BookMarketController(BookStorage bookStorage, Cart cart, ConsoleView view) {
		this.view = view;
		mBookStorage = bookStorage;
		mCart = cart;
		mAdmin = new Admin();
	}

	public void start() {
		welcome();
		registerCustomerInfo();
		
		int menu;
		
		do {
			menu = view.selectMenu(menuList);
			
			switch (menu) {
			case 1 -> viewBookInfo();
			case 2 -> viewCart();
			case 3 -> addBook2Cart();
			case 4 -> deleteBookInCart();
			case 5 -> updateBookInCart();
			case 6 -> resetCart();
			case 7 -> order();
			case 8 -> adminMode();
			case 0 -> end();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);	
	}

	private void adminMode() {

		if (!authenticateAdmin()) return;
		view.showMessage("\n>> 관리자 모드를 시작합니다.");
		
		
		int menu = view.selectMenu(adminMenuList);
			
		do {
			switch (menu) {
			case 1 -> addBook2Storage();
			case 2 -> deleteBookInStorage();
			case 3 -> saveBookList2File();
			case 0 -> endAdminMode();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);

}

	private void endAdminMode() {
		view.showMessage(">> 관리자 모드를 종료합니다.\n");
	}

	private void saveBookList2File() {

	}

	private void deleteBookInStorage() {
		
	}

	private void addBook2Storage() {

	}

	private boolean authenticateAdmin() {
		view.showMessage("관리자 모드 진입을 위한 관리자 id와 password 확인");
		String id = view.inputString("관리자 ID : ");
		String password = view.inputString("관리자 Password : ");
		if (!mAdmin.login(id, password)) {
			view.showMessage("관리자 ID 또는 Password가 잘못 입력되었습니다.");
			return false;
		}
		return true;
	}

	// 환영 인사
	private void welcome() {
		view.displayWelcome();
	}
	
	// 고객 정보 등록
	private void registerCustomerInfo() {
		mCustomer = new Customer();
		view.inputCustomerInfo(mCustomer);
	}

	// 도서 정보 보기
	private void viewBookInfo() {
		view.displayBookInfo(mBookStorage);
	}
	
	// 장바구니 보기
	private void viewCart() {
		view.displayCart(mCart);
	}

	// 장바구니에 도서 추가
	private void addBook2Cart() {
		view.displayBookInfo(mBookStorage);
		int bookId = view.selectBookId(mBookStorage);
		mCart.addItem(mBookStorage.getBookId(bookId));
		view.showMessage(">>장바구니에 도서를 추가하였습니다.");	
	}
	
	// 장바구니 도서 삭제
	private void deleteBookInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int bookId = view.selectBookId(mCart);
			if (view.askConfirm(">> 해당 도서를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				// 해당 도서 ID의 cartItem 삭제
				mCart.deleteItem(bookId);
				view.showMessage(">> 해당 도서를 삭제했습니다.");
			}
		}
	}
	
	// 장바구니 도서 수량 변경
	private void updateBookInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 도서 ID 입력 받기
			int bookId = view.selectBookId(mCart);
			// 수량 입력 받기
			int quantity = view.inputQuantity(0, mBookStorage.getMaxQuantity());
			// 도서 ID에 해당하는 cartItem 가져와서 cartItem quantity set 수량
			mCart.updateQuantity(bookId, quantity);
		}
	}

	// 장바구니 비우기
	private void resetCart() {
		view.displayCart(mCart);
		
		if (!mCart.isEmpty()) {
			if (view.askConfirm(">> 장바구니를 비우려면 yes를 입력하세요 : ", "yes")) {
				mCart.resetCart();
				view.showMessage(">> 장바구니를 비웠습니다.");
			}
		}
		
	}
	
	// 주문
	private void order() {
		// 배송 정보 추가
		getDeliveryInfo();
		// 구매 정보 보기 : 장바구니 내역, 배송 정보
		viewOrderInfo();
		// 진짜 주문할거니?
		if (view.askConfirm("진짜 주문하려면 yes를 입력하세요 : ", "yes") ) {
			// 주문 처리 -> 장바구니 초기화
			mCart.resetCart();
		}
	}
	
	private void getDeliveryInfo() {
		view.inputDeliveryInfo(mCustomer);	
	}

	private void viewOrderInfo() {
		view.displayOrder(mCart, mCustomer);
	}
	
	// 종료
	private void end() {
		view.showMessage(">> Hyejeong Book Market을 종료합니다.");
	}













}
