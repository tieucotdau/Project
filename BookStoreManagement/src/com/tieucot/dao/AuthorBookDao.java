package com.tieucot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tieucot.core.Author;
import com.tieucot.core.Book;
import com.tieucot.dbconnection.DBConnection;

public class AuthorBookDao {
	private Connection conn;
	private static AuthorDao authorDao = new AuthorDao();

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
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

	public boolean addNewBookAuthor(Author author, Book book,double revenueShareDouble) throws SQLException {

		String query = "insert into bs_author_book(id,book_id,author_id,revenue_share) values (" + generateId() + ",'" + book.getId()
				+ "','" + author.getId() + "',"+revenueShareDouble+")";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}
	public boolean deleteAuthorBookByBook(long book_id) throws SQLException {
		String query = "delete from bs_author_book where book_id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, book_id);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	

	public ArrayList<Author> findAuthors4EachBook(Book book) throws SQLException {
		String query = "select author_id from bs_author_book where book_id =" + book.getId();
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		if (resultSet.next()) {
			long authorId = resultSet.getLong("author_id");
			Author author = authorDao.findAuthorById(authorId);
			authorList.add(author);
		}
		return authorList;
	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_author_book ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}
}
