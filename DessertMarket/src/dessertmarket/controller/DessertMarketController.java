package dessertmarket.controller;

import dessertmarket.model.Admin;
import dessertmarket.model.DessertStorage;
import dessertmarket.model.Cart;
import dessertmarket.model.Customer;
import dessertmarket.view.ConsoleView;

public class DessertMarketController {

	ConsoleView view;
	DessertStorage mDessertStorage;
	Cart mCart;
	Customer mCustomer;
	Admin mAdmin;
	
	String[] menuList = {
			"0. 종료",
			"1. 메뉴 정보 보기",
			"2. 장바구니 보기",
			"3. 장바구니에 메뉴 추가",
			"4. 장바구니 메뉴 삭제",
			"5. 장바구니 메뉴 수량 변경",
			"6. 장바구니 비우기",
			"7. 주문",
			"8. 관리자 모드"
	};
	
	String[] adminMenuList = {

			"0. 종료",
			"1. 메뉴 정보 추가",
			"2. 메뉴 정보 삭제",
			"3. 메뉴 정보 보기",
			"4. 메뉴 정보 파일 저장"

	};
	
	public DessertMarketController(DessertStorage dessertStorage, Cart cart, ConsoleView view) {
		this.view = view;
		mDessertStorage = dessertStorage;
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
			case 1 -> viewDessertInfo();
			case 2 -> viewCart();
			case 3 -> addDessert2Cart();
			case 4 -> deleteDessertInCart();
			case 5 -> updateDessertInCart();
			case 6 -> resetCart();
			case 7 -> order();
			case 8 -> adminMode();
			case 0 -> end();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);	
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
	private void viewDessertInfo() {
		view.displayDessertInfo(mDessertStorage);
	}
	
	// 장바구니 보기
	private void viewCart() {
		view.displayCart(mCart);
	}

	// 장바구니에 도서 추가
	private void addDessert2Cart() {
		view.displayDessertInfo(mDessertStorage);
		int dessertId = view.selectDessertId(mDessertStorage);
		mCart.addItem(mDessertStorage.getDessertById(dessertId));
		view.showMessage(">>장바구니에 메뉴를 추가하였습니다.");	
	}
	
	// 장바구니 도서 삭제
	private void deleteDessertInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 메뉴 ID 입력 받기
			int dessertId = view.selectDessertId(mCart);
			if (view.askConfirm(">> 해당 메뉴를 삭제하려면 yes를 입력하세요 : ", "yes")) {
				// 해당 메뉴 ID의 cartItem 삭제
				mCart.deleteItem(dessertId);
				view.showMessage(">> 해당 메뉴를 삭제했습니다.");
			}
		}
	}
	
	// 장바구니 메뉴 수량 변경
	private void updateDessertInCart() {
		// 장바구니 보여주기
		view.displayCart(mCart);
		if (!mCart.isEmpty()) {
			// 메뉴 ID 입력 받기
			int dessertId = view.selectDessertId(mCart);
			// 수량 입력 받기
			int quantity = view.inputQuantity(0, mDessertStorage.getMaxQuantity());
			// 메뉴 ID에 해당하는 cartItem 가져와서 cartItem quantity set 수량
			mCart.updateQuantity(dessertId, quantity);
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
	
	// 관리자 모드
	private void adminMode() {
		
		if (!authenticateAdmin()) {
			view.showMessage("관리자 모드로 전환할 수 없습니다.");
			return;
		}
		
		// 관리자 모드 진입 -> 메뉴 추가, 메뉴 삭제, 메뉴 정보 파일 저장
			// 관리자 모드일 때의 메뉴 출력
			// 메뉴 선택하면 해당 기능 실행
		int menu;
		do {
			menu = view.selectMenu(adminMenuList);
			
			switch (menu) {
			case 1 -> addDessert2Storage();
			case 2 -> deleteDessertInStorage();
			case 3 -> viewDessertInfo();
			case 4 -> saveDessertList2File();
			case 0 -> adminEnd();
			default -> view.showMessage("잘못된 메뉴 번호입니다.");
			}
		} while (menu != 0);
	}

	// 관리자 인증 (id, password 확인)
	private boolean authenticateAdmin() {
		view.showMessage("관리자 모드 진입을 위한 관리장 인증");
		String id = view.inputString("관리자 ID : ");
		String password = view.inputString("관리자 password : ");
		return mAdmin.login(id, password);
	}
	
	// Dessert Storage에 도서 추가
	private void addDessert2Storage() {
		view.showMessage("새로운 메뉴를 추가합니다.");
		
		// 메뉴정보로 Dessert 인스턴스 만들어서 mDessertStorage에 추가
		mDessertStorage.addDessert(view.inputString("메뉴 이름 : "),
				view.inputString("칼로리 : "),
				view.readNumber("가격 : "));

	}
	
	// Dessert Storage에서 도서 삭제
	private void deleteDessertInStorage() {
		if (mDessertStorage.isEmpty()) {
			view.showMessage("메뉴 창고에 메뉴가 없습니다.");
			return;
		}
		// 메뉴 창고 보여주기
		viewDessertInfo();
		// 메뉴 ID 입력 받기
		int dessertId = view.selectDessertId(mDessertStorage);
		if (view.askConfirm(">> 해당 메뉴를 삭제하려면 yes를 입력하세요 : ", "yes")) {
			// 해당 메뉴 ID의 cartItem 삭제
			mDessertStorage.deleteItem(dessertId);
			view.showMessage(">> 해당 메뉴를 삭제했습니다.");
		}

	}

	// 메뉴 정보를 파일에 저장
	private void saveDessertList2File() {
		if (mDessertStorage.isSaved()) {
			view.showMessage("메뉴 정보가 파일과 동일합니다.");
		} else {
			mDessertStorage.saveDessertList2File();
			view.showMessage("메뉴 정보를 저장하였습니다.");
		}
	}
	
	// 관리자 모드 종료 : 메뉴 정보 수정 후 파일에 반영되지 않은 경우, 저장 여부 확인
	private void adminEnd() {
		if (!mDessertStorage.isSaved()) {
			view.showMessage("수정한 메뉴정보가 파일로 저장되지 않았습니다.");
			if (view.askConfirm("메뉴정보를 저장하려면 yes를 입력하세요 : ", "yes")) {
				mDessertStorage.saveDessertList2File();
			}
		}
		view.showMessage("관리자 모드가 종료되었습니다.\n");
	}
	
	// 종료
	private void end() {
		view.showMessage(">> JUN Dessert Market을 종료합니다.");
	}













}
