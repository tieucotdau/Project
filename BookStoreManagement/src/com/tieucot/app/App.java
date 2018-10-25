package com.tieucot.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import com.tieucot.service.MenuService;

public class App {
	private static MenuService menuService = new MenuService();
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws ParseException, SQLException {
		boolean flag = true;
		while (flag) {
			menuService.printMenuMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
			case 1:
				menuService.authorMenus();
				break;
			case 2:
				menuService.categoryMenus();
				break;
			case 3:
				menuService.bookMenus();
				break;

			case 4:
				menuService.bookStoreMenus();
				break;

			case 5:
				System.out.println("System end");
				flag = false;
				break;
			}

		}

	}
}
