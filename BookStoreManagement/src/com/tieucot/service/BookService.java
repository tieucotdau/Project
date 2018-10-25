package com.tieucot.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.tieucot.core.Author;
import com.tieucot.core.Book;
import com.tieucot.core.Category;
import com.tieucot.dao.AuthorBookDao;
import com.tieucot.dao.AuthorDao;
import com.tieucot.dao.BookDao;
import com.tieucot.dao.CategoryDao;

public class BookService {
	private static Scanner scanner = new Scanner(System.in);
	private static BookDao bookDao = new BookDao();
	private static CategoryDao categoryDao = new CategoryDao();
	private static AuthorDao authorDao = new AuthorDao();
	private static AuthorBookDao authorBookDao = new AuthorBookDao();

	public static void addNewRealBook() {
		System.out.println("Add new book function");
		try {
			long id = bookDao.generateId();
			System.out.println("Input name");
			String name = scanner.nextLine();
			System.out.println("Input price");
			String price = scanner.nextLine();
			Double priceDouble = Double.parseDouble(price);
			System.out.println("Input soldNumber");
			String soldNumber = scanner.nextLine();
			Long soldNumberDouble = Long.parseLong(soldNumber);
			System.out.println("Choose category ID:");
			ArrayList<Category> categoryList = categoryDao.findAllCatergory();
			for (Category category : categoryList) {
				System.out.println(category);
			}
			String categoryId = scanner.nextLine();
			long categoryIdLong = Long.parseLong(categoryId);
			Category selectedCategory = categoryDao.findCategoryById(categoryIdLong);
			System.out.println("Choose author List, split by - character:");

			ArrayList<Author> authorList = authorDao.findAllAuthor();
			for (Author author : authorList) {
				System.out.println(author);
			}
			// 3232-21-3221
			String authorIDList = scanner.nextLine();
			String[] authors = authorIDList.split("-");
			Book book = new Book(id, name, priceDouble,soldNumberDouble, selectedCategory);
			
			for (String author : authors) {
				Long authorIdLong = Long.parseLong(author);
				Author selectedAuthor = authorDao.findAuthorById(authorIdLong);
				System.out.println("Please choose the revenueShare for this author: ");
				String revenueShare = scanner.nextLine();
				double revenueShareDouble = Double.parseDouble(revenueShare);
				authorBookDao.addNewBookAuthor(selectedAuthor, book,revenueShareDouble);
			}
			
			bookDao.addNewBook(book);
		} catch (Exception e) {
			System.out.println("There is an error when adding new book");
			e.printStackTrace();
		} 
	}

	public static void findBookByName() {
		System.out.println("Find book by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		Book book = null;
		try {
			book = bookDao.findBookByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when finding a book");
			e.printStackTrace();
		}
		if (book != null) {
			System.out.println(book);
		} else {
			System.out.println("Couldn't find the book with name: " + name);
		}
	}

	public static void displayBook() {
		try {
			ArrayList<Book> bookList = bookDao.findBook();
			for (Book book : bookList) {
				System.out.println(book);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all books");
			e.printStackTrace();
		}
	}

	public static void updateBook() {
		try {
			System.out.println("Update book by id function");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Book book = bookDao.findBookById(Long.parseLong(id));
			if (book == null) {
				System.out.println("Couldn't find the book id=" + id);
			} else {
				System.out.println("Found book :" + id);
				System.out.println("With info:" + book);
				System.out.println("Input new name");
				String name = scanner.nextLine();
				System.out.println("Input new price");
				String price = scanner.nextLine();
				Double priceDouble = Double.parseDouble(price);
				System.out.println("Choose category ID:");
				ArrayList<Category> categoryList = categoryDao.findAllCatergory();
				for (Category category : categoryList) {
					System.out.println(category);
				}
				String categoryId = scanner.nextLine();
				Long categoryIdLong = Long.parseLong(categoryId);
				Category selectedCategory = categoryDao.findCategoryById(categoryIdLong);
				System.out.println("Choose author List, split by - character:");

				ArrayList<Author> authorList = authorDao.findAllAuthor();
				for (Author author : authorList) {
					System.out.println(author);
				}
				// 3232-21-3221
				String authorIDList = scanner.nextLine();
				String[] authors = authorIDList.split("-");
				Book newBook = new Book(Long.parseLong(id), name, priceDouble, selectedCategory);
				authorBookDao.deleteAuthorBookByBook(Long.parseLong(id));
				for (String author : authors) {
					Long authorIdLong = Long.parseLong(author);
					Author selectedNewAuthor = authorDao.findAuthorById(authorIdLong);
					System.out.println("Please choose the revenueShare for this author: ");
					String revenueShare = scanner.nextLine();
					double revenueShareDouble = Double.parseDouble(revenueShare);
					authorBookDao.addNewBookAuthor(selectedNewAuthor, newBook,revenueShareDouble);

				}
				
				bookDao.modifyBook(newBook);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a book");
			e.printStackTrace();
		}
	}

	public static void deleteBookByName() {
		System.out.println("Delete book by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		try {
			bookDao.deleteBookByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when deleting a book");
			e.printStackTrace();
		}
	}

	public void printBookMenu() {
		System.out.println("The book management program");
		System.out.println("1. Add new book");
		System.out.println("2. Find book by name");
		System.out.println("3. Display books");
		System.out.println("4. Update book");
		System.out.println("5. Delete book by name");
		System.out.println("6. Exit");
	}
}
