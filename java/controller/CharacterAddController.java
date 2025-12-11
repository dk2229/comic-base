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




@WebServlet("/characterAdd")
public class CharacterAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mangaIdParam = request.getParameter("mangaId");
		request.setAttribute("mangaId", mangaIdParam);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterAdd.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mangaIdParam = request.getParameter("mangaId");
		String charaName = request.getParameter("charaName");
		String charaType = request.getParameter("charaType");
		String explanation = request.getParameter("explanation");
		String first = request.getParameter("first");
		
		int mangaId = Integer.parseInt(mangaIdParam);
		
		Validation validation = new Validation();
		validation.isBlank("キャラクター名", charaName);
		validation.length("キャラクター名", charaName, 1, 255);
		validation.length("説明", explanation, 0, 255);
		
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			request.setAttribute("mangaId", mangaIdParam);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterAdd.jsp");
			rd.forward(request, response);
		} else {
			Character character = new Character(mangaId, charaName, charaType, explanation, first);
			
			CharacterService characterService = new CharacterService();
			boolean result = characterService.characterRegisterDo(character);
			
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterDone.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("registerError", "キャラクター情報の登録に失敗しました。");
				request.setAttribute("mangaId", mangaIdParam);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterAdd.jsp");
				rd.forward(request, response);
			}
		}
	}
}