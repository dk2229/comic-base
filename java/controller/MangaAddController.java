package controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Manga;
import service.MangaService;
import validation.Validation;

@WebServlet("/mangaAdd")
public class MangaAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaAdd.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String number = request.getParameter("number");
		String publisher = request.getParameter("publisher");
		String genre = request.getParameter("genre");
		
		Validation validation = new Validation();
		validation.isBlank("タイトル", title);
		validation.isBlank("作者", author);
		validation.length("タイトル", title, 1, 255);
		validation.length("作者", author, 1, 255);
		
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaAdd.jsp");
			rd.forward(request, response);
		} else {
			Manga manga = new Manga(title, author, number, publisher, genre);
			
			MangaService mangaService = new MangaService();
			boolean result = mangaService.mangaRegisterDo(manga);
			
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaDone.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("registerError", "漫画情報の登録に失敗しました。");
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaAdd.jsp");
				rd.forward(request, response);
			}
		}
	}



}
