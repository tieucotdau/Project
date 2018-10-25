package com.tieucot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tieucot.core.Author;
import com.tieucot.core.User;
import com.tieucot.dbconnection.DBConnection;

public class UserDAO {
	private Connection conn;

	public Connection getConnection() throws SQLException {
		return DBConnection.getDbCon().getConn();
	}
	
	public User findUser(String email,String password) throws SQLException {
		String query = "select * from bs_user where email='" + email + "' and password='"+password+"'";
		Statement stmt = getConnection().createStatement();
		ResultSet resultSet = stmt.executeQuery(query);

		if (resultSet.next()) {
			User user = new User();
			user.setId(resultSet.getLong("id"));
			user.setName(resultSet.getString("name"));
			user.setDob(resultSet.getDate("dob"));
			user.setPassword(resultSet.getString("password"));
			return user;
		} else
			return null;

	}
	public  boolean validate(User user){  
		boolean status=false;  
		try{  
		              
		PreparedStatement ps=getConnection().prepareStatement(  
		    "select * from bs_user where email=? and password=?");  
		  
		ps.setString(1,user.getEmail());  
		ps.setString(2, user.getPassword());  
		              
		ResultSet rs=ps.executeQuery();  
		status=rs.next();  
		              
		}catch(Exception e){}  
		  
		return status;  
		  
		}  
	
}
