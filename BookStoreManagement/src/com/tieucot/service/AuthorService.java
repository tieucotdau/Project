package com.tieucot.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.tieucot.core.Author;
import com.tieucot.dao.AuthorDao;

public class AuthorService {
	public static Scanner scanner = new Scanner(System.in);
	public static AuthorDao authorDAO = new AuthorDao();

	public static void addNewRealAuthor() {
		System.out.println("Add new author function");
		try {
			long id = authorDAO.generateId();
			System.out.println("Input name");
			String name = scanner.nextLine();
			System.out.println("Input dob");
			String dob = scanner.nextLine();
			SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
			Date dobDate=sdf.parse(dob);
			Author author = new Author(id, name, dobDate);
			authorDAO.addNewAuthor(author);
		} catch (Exception e) {
			System.out.println("There is an error when adding new author");
			e.printStackTrace();
		}
	}

	public static void findAuthorByName() {
		System.out.println("Find author by name function");
		System.out.println("Input the name");
		String name = scanner.nextLine();
		Author author = null;
		try {
			author = authorDAO.findAuthorByName(name);
		} catch (Exception e) {
			System.out.println("There is an error when finding a author");
			e.printStackTrace();
		}
		if (author != null) {
			System.out.println(author);
		} else {
			System.out.println("Couldn't find the author with name: " + name);
		}
	}

	public static void displayAuthor() {
		try {
			ArrayList<Author> authorList = authorDAO.findAllAuthor();
			for (Author author : authorList) {
				System.out.println(author);
			}
		} catch (Exception e) {
			System.out.println("There is an error when display all authors");
			e.printStackTrace();
		}
	}

	public static void displayAuthorAbove20(){
		System.out.println("Display author above 20 age");
		try {
			ArrayList<Author> listAuthor = authorDAO.findAuthorAbove20();
			for(Author author :listAuthor){
				System.out.println(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void updateAuthor() {
		try {
			System.out.println("Update author by id function");
			System.out.println("Input id");
			String id = scanner.nextLine();
			Author author = authorDAO.findAuthorById(Long.parseLong(id));
			if (author == null) {
				System.out.println("Couldn't find the author id=" + id);
			} else {
				System.out.println("Found author :" + id);
				System.out.println("With info:" + author);
				System.out.println("Input new name");
				String name = scanner.nextLine();
				System.out.println("Input new email");
				System.out.println("Input dob");
				String dob = scanner.nextLine();
				SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
				Date dobDate=sdf.parse(dob);
				Author newAuthor = new Author(Long.parseLong(id), name,
						dobDate);
				authorDAO.modifyAuthor(newAuthor);

			}

		} catch (Exception e) {
			System.out.println("There is an error when updating a author");
			e.printStackTrace();
		}
	}

	public static void deleteAuthorByName() {
		System.out.println("Delete author by name function");
		System.out.println("Input the name");
		String name=scanner.nextLine();
		try{
			authorDAO.deleteAuthorByName(name);
		}catch(Exception e){
			System.out.println("There is an error when deleting a author");
			e.printStackTrace();
		}
	}
	
	public void printAuthorMenu(){
		System.out.println("The author management program");
		System.out.println("1. Add new author");
		System.out.println("2. Find author by name");
		System.out.println("3. Display authors");
		System.out.println("4. Update author");
		System.out.println("5. Delete author by name");
		System.out.println("6. Exit");
	}
}
