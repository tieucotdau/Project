package com.tieucot.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.tieucot.core.Author;
import com.tieucot.core.Category;
import com.tieucot.dao.AuthorDao;
import com.tieucot.dao.CategoryDao;

class RevenueAuthorComparator implements Comparator<Author>{
	public int compare(Author o1, Author o2){
		if(o1.getRevenue()>o2.getRevenue()){
			return 1;
		}
		if(o1.getRevenue()<o2.getRevenue()){
			return -1;
		}
		return 0;
	}
}
public class BookStoreService {
	public static AuthorDao authorDao = new AuthorDao();
	public static CategoryDao categoryDao = new CategoryDao();
	public static Scanner scanner = new Scanner(System.in);

	public static void topAuthorByRevenue() throws SQLException {
		ArrayList<Author> arrayListAuthor=authorDao.findTopAuthorByRevenue();
		//Collections.sort(arrayListAuthor,new RevenueAuthorComparator());
		for(Author author: arrayListAuthor){
			System.out.println(author);
			System.out.println("Tuoi: "+author.calculateAge());
			System.out.println("Doanh thu ban: "+author.getRevenue());
		}

	}
	
	public void topCategoryByRevenue() throws SQLException {
		ArrayList<Category> arrayListCategory=categoryDao.findTopCategoryByRevenue();
		for(Category c: arrayListCategory){
			System.out.println(c);
			System.out.println("Doanh thu ban: "+c.getRevenue());
		}
		
	}
	public void printBookStoreMenu() {
		System.out.println("1.Top Author by revenue");
		System.out.println("2.Top Category by revenue");
		System.out.println("3.Calculate revenue of day");
		System.out.println("4.Calculate revenue of time");
		System.out.println("5.Exit");
	}


	
}
