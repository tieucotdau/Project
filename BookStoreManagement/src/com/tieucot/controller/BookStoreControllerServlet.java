package com.tieucot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucot.core.Author;
import com.tieucot.core.Category;
import com.tieucot.dao.AuthorDao;
import com.tieucot.dao.BookDao;
import com.tieucot.dao.CategoryDao;

/**
 * Servlet implementation class BookControllerServlet
 */
@WebServlet("/BookStoreControllerServlet")
public class BookStoreControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static CategoryDao categoryDAO = new CategoryDao();
	public static AuthorDao authorDAO = new AuthorDao();
	public static BookDao bookDAO = new BookDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing Books
			if (theCommand == null) {
				theCommand = "LIST";
			}

			// route to the appropriate method
			switch (theCommand) {

			case "TOP_AUTHOR_REVENUE":
				topAuthorByRevenue(request, response);
				break;
			case "TOP_CATEGORY_REVENUE":
				topCategoryByRevenue(request, response);
				break;

			default:
				showAll(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	public static void topCategoryByRevenue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ArrayList<Category> categoryList = categoryDAO.findAllCatergory();
			Collections.sort(categoryList, Category.compare);
			System.out.println("Top Category by revenue");
			request.setAttribute("category_List", categoryList);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/report/category_revenue.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void topAuthorByRevenue(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ArrayList<Author> authorList = authorDAO.findAllAuthor();
			Collections.sort(authorList, Author.compare);
			System.out.println("Top Category by revenue");
			request.setAttribute("author_List", authorList);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/report/author_revenue.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void showAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			ArrayList<Category> categoryList = categoryDAO.findAllCatergory();
			Collections.sort(categoryList, Category.compare);
			System.out.println("Top Category by revenue");
			request.setAttribute("category_List", categoryList);
			ArrayList<Author> authorList = authorDAO.findAllAuthor();
			Collections.sort(authorList, Author.compare);
			System.out.println("Top Author by revenue");
			request.setAttribute("author_List", authorList);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/report/index.jsp");
			try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
