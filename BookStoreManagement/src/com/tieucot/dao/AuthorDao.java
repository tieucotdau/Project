package com.tieucot.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import com.tieucot.core.Author;
import com.tieucot.core.Book;
import com.tieucot.dbconnection.DBConnection;

public class AuthorDao {
	private Connection conn;

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}

	public double calculateRevenueOfAuthor(long authorId) throws SQLException {
		long revenueSum=0;
		String query="select ba.id as id ,ba.name as name,ifnull(sum(bab.revenue_share*bb.price*bb.sold_number) ,0) as Revenue "
				+ " from bs_author ba left join bs_author_book bab on ba.id = bab.author_id left join bs_book bb on bab.book_id = bb.id where ba.id="+authorId
				+ " group by ba.id "
				+ " order by Revenue "
				+ " Desc";
		Statement stmt=getConnection().createStatement();
		ResultSet resultSet=stmt.executeQuery(query);
		while(resultSet.next()){
			return resultSet.getDouble("Revenue");
		
		}
		return revenueSum;
	}
	public ArrayList<Author> findTopAuthorByRevenue() throws SQLException {
	String query = " select bs_author.id,bs_author.name,bs_author.dob,ifnull((bs_author_book.revenue_share*bs_book.price*bs_book.sold_number) ,0) as Revenue " + 
   " from bs_author left join bs_author_book on bs_author.id = bs_author_book.author_id left join bs_book on bs_author_book.book_id = bs_book.id " +
		" order by Revenue " +
		 " Desc " ;
	Statement stmt = getConnection().createStatement();
	ResultSet resultSet = stmt.executeQuery(query);
	ArrayList<Author> listAuthor = new ArrayList<Author>();
	while(resultSet.next())
	{    Author author = new Author();
	         author.setId(resultSet.getLong("bs_author.id"));
	         author.setName(resultSet.getString("bs_author.name"));
	         author.setRevenue(resultSet.getDouble("Revenue"));
	         author.setDob(resultSet.getDate("bs_author.dob"));
		listAuthor.add(author);
	}
	    return listAuthor;
	}
	public ArrayList<Author> findAllAuthor() throws SQLException {
		return findAllAuthor(null);
	}
	public ArrayList<Author> findAllAuthor(String name) throws SQLException {
		String query = "select * from bs_author  ";
		if(name!=null){
			query+=" where name like '%"+name+"%'";
		}
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		while (resultSet.next()) {
			Author author = new Author(resultSet);
			double revenue = calculateRevenueOfAuthor(resultSet.getLong("id"));
			author.setRevenue(revenue);
			authorList.add(author);
		}

		return authorList;
	}
	public ArrayList<Author> findAuthor(int start, int end) throws SQLException {
		return findAuthor(null, start, end);
	}
	public ArrayList<Author> findAuthor(String name,int start, int end) throws SQLException {
		String query = "SELECT * FROM bs_author ";
		if(name!=null){
			query+=" where name like '%"+name+"%'";
		}
		query+=" limit "+start+","+end;
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		while (resultSet.next()) {
			Author author = new Author(resultSet);
			double revenue = calculateRevenueOfAuthor(resultSet.getLong("id"));
			author.setRevenue(revenue);
			authorList.add(author);
		}

		return authorList;
	}

	public ArrayList<Author> findAuthorOfBook(long bookId) throws SQLException {
		String query = "SELECT bs_author.id, bs_author.NAME, bs_author.dob FROM bs_book, bs_author,bs_author_book WHERE  "
				+ bookId + " = bs_author_book.book_id and bs_author.id = bs_author_book.author_id";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		if (resultSet.next()) {
			Author author = new Author(resultSet);
			authorList.add(author);
		}
		return authorList;
	}

	public boolean addNewAuthor(Author author) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String query = "insert into bs_author(id,name,dob) values (" + author.getId() + ",'" + author.getName() + "','"
				+ sdf.format(author.getDob()) + "')";
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean modifyAuthor(Author author) throws SQLException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String query = "update bs_author set name='" + author.getName() + "' , dob='" + sdf.format(author.getDob())
				+ "' where id=" + author.getId();
		Statement stmt = getConnection().createStatement();
		int n = stmt.executeUpdate(query);
		// return (n!=0);
		if (n != 0)
			return true;
		return false;
	}

	public boolean deleteAuthorByName(String name) throws SQLException {
		String query = "delete from bs_author where name = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setString(1, name);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}
	public boolean deleteAuthorById(long id) throws SQLException {
		String query = "delete from bs_author where id = ?";
		java.sql.PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setLong(1, id);
		int n = stmt.executeUpdate();
		// return (n!=0);
		if (n != 0) {
			System.out.println(n + " rows deleted");
		}
		return false;
	}

	public Author findAuthorByName(String name) throws SQLException {
		String query = "select * from bs_author where name='" + name + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Author author = new Author(resultSet);
			return author;
		} else
			return null;

	}

	public Author findAuthorById(long id) throws SQLException {
		String query = "select * from bs_author where id='" + id + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			Author author = new Author(resultSet);
			return author;
		} else
			return null;

	}

	public long generateId() throws SQLException {
		String query = "select max(id) as maxId from bs_author ";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		if (resultSet.next()) {
			return resultSet.getLong("maxId") + 1;
		} else {
			return 0;
		}
	}

	public ArrayList<Author> findAuthorAbove20() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, -20);
		java.util.Date date = calendar.getTime();

		String query = "select * from bs_author where dob <  '" + sdf.format(date) + "'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);
		ArrayList<Author> authorList = new ArrayList<Author>();

		while (resultSet.next()) {
			Author author = new Author(resultSet);
			double revenue = calculateRevenueOfAuthor(resultSet.getLong("id"));
			author.setRevenue(revenue);
			authorList.add(author);
		}
		return authorList;
	}
}
