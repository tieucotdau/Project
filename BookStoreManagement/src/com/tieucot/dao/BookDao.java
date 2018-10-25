package com.tieucot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tieucot.core.Book;
import com.tieucot.dbconnection.DBConnection;

public class BookDao {
	private Connection conn;

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}
	public BookDao(){
		
	}
	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * public long caculateSoldNumber(long bookId) throws SQLException { long
	 * soldNum = 0l; String query =
	 * "SELECT bs_order_detail.quantity FROM bs_book, bs_order_detail WHERE  " +
	 * bookId + " = bs_order_detail.book_id"; Statement stmt =
	 * getConnection().createStatement(); ResultSet resultSet =
	 * stmt.executeQuery(query);
	 * 
	 * if (resultSet.next()) { soldNum +=
	 * resultSet.getLong("bs_order_detail.quantity"); } return soldNum; }
	 */
	public ArrayList<Book> findBook() throws SQLException {
		String query = "SELECT * FROM bs_book ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Book> bookList = new ArrayList<Book>();

		while (resultSet.next()) {
			Book book = new Book(resultSet);
			bookList.add(book);
		}
		return bookList;
	}
	public ArrayList<Book> findBook(int start, int end) throws SQLException {
		String query = "SELECT * FROM bs_book limit "+start+","+end;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Book> bookList = new ArrayList<Book>();

		while (resultSet.next()) {
			Book book = new Book(resultSet);
			bookList.add(book);
		}
		return bookList;
	}
	

	public boolean addNewBook(Book book) throws SQLException {
		String query = "insert into bs_book(id,name,price,category_id,sold_number) values (" + book.getId() + ",'" + book.getName()
				+ "'," + book.getPrice() + "," + book.getCategory().getId()+ "," + book.getSoldNumber() + ")";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyBook(Book book) throws SQLException {
		String query = "update bs_book set name='" + book.getName() + "' , price='" + book.getPrice()
				+ "' , category_id='" + book.getCategory().getId() + "' where id=" + book.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		if (n != 0)
			return true;
		return false;
	}
	
	public boolean deleteBookById(long id) throws SQLException {
		String query = "delete from bs_book where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	public boolean deleteBookByName(String name) throws SQLException {
		String query = "delete from bs_book where name = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public Book findBookByName(String name) throws SQLException {
		String query = "select * from bs_book where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Book book = new Book(resultSet);
			return book;
		} else
			return null;

	}

	public Book findBookById(long id) throws SQLException {
		String query = "select * from bs_book where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Book book = new Book(resultSet);
			return book;
		} else
			return null;

	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_book";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;

		}
	}
	public double getSoldNumberPrice(long id) throws SQLException{
		String query="select sold_number, price from bs_book where id="+id;
		Statement stmt=getConnection().createStatement();
		ResultSet resultSet=stmt.executeQuery(query);
		resultSet.next();
		double t=resultSet.getDouble("price")*resultSet.getLong("sold_number");
		return t;
	}
}
