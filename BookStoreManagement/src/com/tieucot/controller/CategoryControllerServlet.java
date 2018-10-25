package com.tieucot.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucot.core.Category;
import com.tieucot.dao.CategoryDao;

/**
 * Servlet implementation class CategoryControllerServlet
 */
@WebServlet("/CategoryControllerServlet")
public class CategoryControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static CategoryDao categoryDAO = new CategoryDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// if the command is missing, then default to listing Categorys
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// route to the appropriate method
			switch (theCommand) {
			
			case "LIST":
				listCategorys(request, response);
				break;
				
			case "ADD":
				addCategory(request, response);
				break;
			 case "NEW":
				 newCategory(request, response);
				 break;
			case "LOAD":
				loadCategory(request, response);
				break;
				
			case "UPDATE":
				updateCategory(request, response);
				break;
			
			case "DELETE":
				deleteCategory(request, response);
				break;
			default:
				listCategorys(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}

	
	private void newCategory(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/category/add-category-form.jsp");
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
	private void deleteCategory(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Category id from form data
		String theCategoryId = request.getParameter("categoryId");
		
		// delete Category from database
		categoryDAO.deleteCategoryById(Long.parseLong(theCategoryId));
		
		// send them back to "list Categorys" page
		listCategorys(request, response);
	}

	private void updateCategory(HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		// read Category info from form data
		int id = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("name");
		// create a new Category object
		Category theCategory = new Category(id, name);
		
		// perform update on database
		categoryDAO.modifyCategory(theCategory);
		
		// send them back to the "list Categorys" page
		listCategorys(request, response);
		
	}

	private void loadCategory(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// read Category id from form data
		String theCategoryId = request.getParameter("categoryId");
		
		// get Category from database (db util)
		Category theCategory = categoryDAO.findCategoryById(Long.parseLong(theCategoryId));
		
		// place Category in the request attribute
		request.setAttribute("the_Category", theCategory);
		
		// send to jsp page: update-Category-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/category/update-category-form.jsp");
		dispatcher.forward(request, response);		
	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category info from form data
		String name = request.getParameter("name");

		// create a new Category object
		Category theCategory = new Category(categoryDAO.generateId(),name);
		
		// add the Category to the database
		categoryDAO.addNewCategory(theCategory);
				
		// send back to main page (the Category list)
		listCategorys(request, response);
	}

	private void listCategorys(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// get Categorys from db util
		List<Category> Categorys = categoryDAO.findAllCatergory();
		
		// add Categorys to the request
		request.setAttribute("CATEGORY_LIST", Categorys);
				
		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/category/list-category.jsp");
		dispatcher.forward(request, response);
	}

}
