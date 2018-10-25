package com.tieucot.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tieucot.core.Author;
import com.tieucot.dao.AuthorDao;

/**
 * Servlet implementation class AuthorServletController
 */
@WebServlet("/AuthorServletController")
public class AuthorServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static AuthorDao authorDao = new AuthorDao();
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			// read the "command" parameter
			String theCommand = request.getParameter("command");

			// if the command is missing, then default to listing Categorys
			if (theCommand == null) {
				theCommand = "LIST";
			}
			String name=request.getParameter("name");

			// route to the appropriate method
			switch (theCommand) {

			case "LIST":
				listAuthor(request, response,name);
				break;

			case "NEW":
				newAuthor(request, response);
				break;
			case "ADD":
				addAuthor(request, response);
				break;

			case "LOAD":
				loadAuthor(request, response);
				break;

			case "UPDATE":
				updateAuthor(request, response);
				break;

			case "DELETE":
				deleteAuthor(request, response);
				break;
			default:
				listAuthor(request, response);
			}

		} catch (Exception exc) {
			throw new ServletException(exc);
		}

	}

	private void addNewAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException {
		String name = request.getParameter("nameAuthor");
		String stringDate = request.getParameter("dobString");

		Date date = sdf.parse(stringDate);
		Author theAuthor = new Author(authorDao.generateId(), name, date);
		authorDao.addNewAuthor(theAuthor);
		try {
			listAuthor(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void newAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/author/add-author-form.jsp");
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

	private void addAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException, ServletException, IOException {
		Long id = authorDao.generateId();
		String name = request.getParameter("nameAuthor");
		String dobString = request.getParameter("dobString");
		Date dob = sdf.parse(dobString);
		// create a new Category object
		Author theAuthor = new Author(id, name, dob);

		// perform update on database
		authorDao.addNewAuthor(theAuthor);

		// send them back to the "list Categorys" page
		listAuthor(request, response);

	}

	private void listAuthor(HttpServletRequest request, HttpServletResponse response,String name)
			throws ParseException, SQLException, ServletException, IOException {
		// get Categorys from db util
		List<Author> authorList = authorDao.findAllAuthor(name);
		if(name!=null){
			request.setAttribute("search_name", name);
		}
		//added
		int count =authorList.size();
		int pageCount=count/2;
		if(count%2!=0){
			pageCount+=1;
		}
		String spageid=request.getParameter("page");
		if(spageid==null){
			spageid="1";
		}
		Integer pageNum=Integer.parseInt(spageid);
		authorList = authorDao.findAuthor(name,(pageNum-1)*2,2);
		// add Orders to the request
		request.setAttribute("order_page_count", pageCount);
		// send to JSP page (view)
		// add Books to the request
		//end added
		// add Categorys to the request
		request.setAttribute("AUTHOR_LIST", authorList);

		// send to JSP page (view)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/author/list-author.jsp");
		dispatcher.forward(request, response);
	}
	private void listAuthor(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, SQLException, ServletException, IOException {
		listAuthor(request,response, null);
	}
	private void deleteAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category id from form data
		String authorId = request.getParameter("authorId");

		// delete Category from database
		authorDao.deleteAuthorById(Long.parseLong(authorId));

		// send them back to "list Categorys" page
		listAuthor(request, response);
	}

	private void updateAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category info from form data
		int id = Integer.parseInt(request.getParameter("authorId"));
		String name = request.getParameter("name");
		String dobString = request.getParameter("dob");
		Date dob = sdf.parse(dobString);
		// create a new Category object
		Author theAuthor = new Author(id, name, dob);

		// perform update on database
		authorDao.modifyAuthor(theAuthor);

		// send them back to the "list Categorys" page
		listAuthor(request, response);

	}

	private void loadAuthor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// read Category id from form data
		String authorId = request.getParameter("authorId");

		Author theAuthor = authorDao.findAuthorById(Long.parseLong(authorId));

		request.setAttribute("the_Author", theAuthor);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/author/update-author-form.jsp");
		dispatcher.forward(request, response);
	}

}
