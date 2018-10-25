package com.tieucot.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.tieucot.core.Category;
import com.tieucot.dao.CategoryDao;

public class CategoryService {
	public static CategoryDao categoryDAO = new CategoryDao();
	private static Scanner scanner = new Scanner(System.in);

	public static void addNewRealCategory() {
		System.out.println("Add new category function");
		try {
			long id = categoryDAO.generateId();
			System.out.println("Input name");
			String name = scanner.nextLine();
			
			Category category = new Category(id, name);
			categoryDAO.addNewCategory(category);
		} catch (Exception e) {
			System.out.println("There is an error when adding new category");
			e.printStackTrace();
		}
	}

	public static void findCategoryByName() {
		System.out.println("Find category by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		Category category = null;
		try {
			category = categoryDAO.findCategoryByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when finding a category");
			e.printStackTrace();
		}
		if (category != null) {
			System.out.println(category);
		} else {
			System.out.println("Couldn't find the category with name: " + name);
		}
	}

	public static void displayCategory() {
		try {
			ArrayList<Category> categoryList = categoryDAO.findAllCatergory();
			for (Category category : categoryList) {
				System.out.println(category);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all categorys");
			e.printStackTrace();
		}
	}

	public static void updateCategory() {
		try {
			System.out.println("Update category by id function");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Category category = categoryDAO.findCategoryById(Long.parseLong(id));
			if (category == null) {
				System.out.println("Couldn't find the category id=" + id);
			} else {
				System.out.println("Found category :" + id);
				System.out.println("With info:" + category);
				System.out.println("Input new name");
				String name = scanner.nextLine();
			
				Category newCategory = new Category(Long.parseLong(id), name);
				categoryDAO.modifyCategory(newCategory);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a category");
			e.printStackTrace();
		}
	}

	public static void deleteCategoryByName() {
		System.out.println("Delete category by name function");
		System.out.println("Input the name");
		String name=scanner.nextLine();
		try{
			categoryDAO.deleteCategoryByName(name);
		}catch(Exception e){
			System.out.println("There is an error when deleting a category");
			e.printStackTrace();
		}
	}
	
	public void printCategoryMenu(){
		System.out.println("The category management program");
		System.out.println("1. Add new category");
		System.out.println("2. Find category by name");
		System.out.println("3. Display categorys");
		System.out.println("4. Update category");
		System.out.println("5. Delete category by name");
		System.out.println("6. Exit");
	}
}
