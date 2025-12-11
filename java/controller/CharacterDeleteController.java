package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Character;
import service.CharacterService;

@WebServlet("/characterDelete")
public class CharacterDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String charaIdParam = request.getParameter("charaId");
		int charaId = Integer.parseInt(charaIdParam);
		
		CharacterService characterService = new CharacterService();
		Character character = characterService.getCharacterById(charaId);
		
		if(character == null) {
			response.sendRedirect("/miniApp/mangaList");
			return;
		}
		
		request.setAttribute("character", character);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterDeleteConfirm.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String charaIdParam = request.getParameter("charaId");
		int charaId = Integer.parseInt(charaIdParam);
		
		CharacterService characterService = new CharacterService();
		Character character = characterService.getCharacterById(charaId);
		
		if(character == null) {
			response.sendRedirect("/miniApp/mangaList");
			return;
		}
		
		boolean result = characterService.characterDeleteDo(character);
		
		if(result == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterDeleteDone.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("deleteError", "キャラクター情報の削除に失敗しました。");
			request.setAttribute("character", character);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterDeleteConfirm.jsp");
			rd.forward(request, response);
		}
	}
}