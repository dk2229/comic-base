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


@WebServlet("/mangaEdit")
public class MangaEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mangaIdParam = request.getParameter("mangaId");
		int mangaId = Integer.parseInt(mangaIdParam);
		
		MangaService mangaService = new MangaService();
		Manga manga = mangaService.getMangaById(mangaId);
		
		request.setAttribute("manga", manga);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaEdit.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mangaIdParam = request.getParameter("mangaId");
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String number = request.getParameter("number");
		String publisher = request.getParameter("publisher");
		String genre = request.getParameter("genre");
		
		int mangaId = Integer.parseInt(mangaIdParam);
		
		Validation validation = new Validation();
		validation.isBlank("タイトル", title);
		validation.isBlank("作者", author);
		validation.length("タイトル", title, 1, 255);
		validation.length("作者", author, 1, 255);
		
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			
			Manga manga = new Manga(title, author, number, publisher, genre);
			manga.setMangaId(mangaId);
			request.setAttribute("manga", manga);
			
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaEdit.jsp");
			rd.forward(request, response);
		} else {
			Manga manga = new Manga(title, author, number, publisher, genre);
			manga.setMangaId(mangaId);
			
			MangaService mangaService = new MangaService();
			boolean result = mangaService.mangaEditDo(manga);
			
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaEditDone.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("editError", "漫画情報の更新に失敗しました。");
				request.setAttribute("manga", manga);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaEdit.jsp");
				rd.forward(request, response);
			}
		}
	}
}
