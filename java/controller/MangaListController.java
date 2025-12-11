package controller;

	import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Manga;
import service.MangaService;

	
	@WebServlet("/mangaList")
	public class MangaListController extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			MangaService mangaService = new MangaService();
			List<Manga> mangaList = mangaService.getMangaList();
			
			request.setAttribute("mangaList", mangaList);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/mangaList.jsp");
			rd.forward(request, response);
		}
	}


