package com.tieucot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tieucot.core.User;
import com.tieucot.dao.UserDAO;

/**
 * Servlet implementation class AuthorServletController
 */
@WebServlet("/UserServletController")
public class UserServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static UserDAO userDao = new UserDAO();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			invalidateUserSession(request, response);

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}
	
	private void invalidateUserSession(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException {
		HttpSession session=request.getSession();  
	    //session.setAttribute("user",null);
	    session.invalidate();
	    request.setAttribute("msg", "Logout Successfully");
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			User user =userDao.findUser(email, password);
			if(user==null){
				request.setAttribute("msg", "Wrong password or email");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else{
				HttpSession session=request.getSession();  
			    session.setAttribute("user",user);
			    response.sendRedirect("BookStoreControllerServlet");  

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
