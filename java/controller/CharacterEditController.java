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
import validation.Validation;

@WebServlet("/characterEdit")
public class CharacterEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String charaIdParam = request.getParameter("charaId");
		int charaId = Integer.parseInt(charaIdParam);
		
		CharacterService characterService = new CharacterService();
		Character character = characterService.getCharacterById(charaId);
		
		request.setAttribute("character", character);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterEdit.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String charaIdParam = request.getParameter("charaId");
		String charaName = request.getParameter("charaName");
		String charaType = request.getParameter("charaType");
		String explanation = request.getParameter("explanation");
		String first = request.getParameter("first");
		
		int charaId = Integer.parseInt(charaIdParam);
		
		CharacterService characterService = new CharacterService();
		Character originalCharacter = characterService.getCharacterById(charaId);
		
		if(originalCharacter == null) {
			response.sendRedirect("/miniApp/mangaList");
			return;
		}
		
		Validation validation = new Validation();
		validation.isBlank("キャラクター名", charaName);
		validation.length("キャラクター名", charaName, 1, 255);
		validation.length("説明", explanation, 0, 255);
		
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			request.setAttribute("character", originalCharacter);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterEdit.jsp");
			rd.forward(request, response);
		} else {
			Character updatedCharacter = new Character(originalCharacter.getMangaId(), charaName, charaType, explanation, first);
			updatedCharacter.setCharaId(charaId);
			
			boolean result = characterService.characterEditDo(updatedCharacter);
			
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterEditDone.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("editError", "キャラクター情報の更新に失敗しました。");
				request.setAttribute("character", originalCharacter);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterEdit.jsp");
				rd.forward(request, response);
			}
		}
	}
}
