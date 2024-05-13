package NoOOP;

import java.util.Scanner;

public class HyejeongBookMarket {
	
	static final int NUM_MENU = 4;

	public static void main(String[] args) {
		displayWelcome();
		
		boolean quit = false;
		while (!quit) {
			int menu = displayGetMenu();
			switch (menu) {
			case 1:
				menuBookList();
				break;
			case 2:
				menuCartList();
				break;
			case 3:
				menuAddCartItem();
				break;
			case 4:
				menuClearCart();
				break;
			case 0:
				menuExit();
				quit = true;
				break;
			default:
				menuWrongNumber();
			}
			
		}

	}
	
	static void displayWelcome() {
		String welcome = "*****************************************\n"
				+ "*    Welcome to Hyejeong Book Market    *\n"
				+ "*****************************************";
		System.out.println(welcome);
	}
	
	static int displayGetMenu() {
		String menuStr = "=========================================\n"
				+ "1. 도서 목록 보기\n"
				+ "2. 장바구니 보기\n"
				+ "3. 장바구니에 도서 추가\n"
				+ "4. 장바구니 비우기\n"
				+ "0. 종료\n"
				+ "=========================================";
		
		System.out.println(menuStr);
		System.out.print(">> 메뉴 선택 :"); 
		Scanner input = new Scanner(System.in);
		return input.nextInt();
	}

	static void menuBookList() {
		System.out.println("BookList");
	}
	
	static void menuCartList() {
		System.out.println("CartList");
	}
	
	static void menuAddCartItem() {
		System.out.println("AddCartItem");
	}
	
	static void menuClearCart() {
		System.out.println("ClearCart");
	}
	
	static void menuExit() {
		System.out.println(">> Hyejeong Book Market을 종료합니다.");
	}
	
	static void menuWrongNumber() {
		System.out.println("없는 메뉴입니다. 0번부터 " + NUM_MENU + "번까지의 메뉴 중에서 선택하세요.");
	}
}
