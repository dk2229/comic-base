package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.Character;
import domain.Manga;
import service.CharacterService;
import service.MangaService;

@WebServlet("/characterDictionary")
public class CharacterDictionaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mangaIdParam = request.getParameter("mangaId");
		int mangaId = Integer.parseInt(mangaIdParam);
		
		// 漫画情報取得
		MangaService mangaService = new MangaService();
		Manga manga = mangaService.getMangaById(mangaId);
		
		// キャラクター一覧取得
		CharacterService characterService = new CharacterService();
		List<Character> characterList = characterService.getCharacterListByMangaId(mangaId);
		
		request.setAttribute("manga", manga);
		request.setAttribute("characterList", characterList);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/characterDictionary.jsp");
		rd.forward(request, response);
	}
}
