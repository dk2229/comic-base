package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.BoardPost;
import domain.User;
import service.BoardPostService;
import validation.Validation;



@WebServlet("/boardPost")
public class BoardPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mangaIdParam = request.getParameter("mangaId");
		String name = request.getParameter("name");
		String content = request.getParameter("content");
		
		if(mangaIdParam == null || mangaIdParam.trim().isEmpty()) {
	        response.sendRedirect("/miniApp/mangaList?error=1");
	        return;
	    }
		
		int mangaId = Integer.parseInt(mangaIdParam);
		
		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		Integer userId = null;
		if(user != null) {
			userId = user.getId();
		}
		
		// 名前が空の場合は「名無しさん」
		if(name == null || name.trim().isEmpty()) {
			name = "名無しさん";
		}
		
		Validation validation = new Validation();
		validation.isBlank("書き込み内容", content);
		validation.length("書き込み内容", content, 1, 1000);
		
		if(validation.hasErrorMsg()) {
			// エラーがある場合はreviewListに戻る
			response.sendRedirect("/miniApp/reviewList?mangaId=" + mangaId + "&error=1");
		} else {
			BoardPost boardPost = new BoardPost(mangaId, 0, name, content, userId);
			
			BoardPostService boardPostService = new BoardPostService();
			boolean result = boardPostService.boardPostRegisterDo(boardPost);
			
			if(result == true) {
			    // 投稿成功 - 通常通りreviewListに戻る
			    response.sendRedirect("/miniApp/reviewList?mangaId=" + mangaId);
			} else {
			    // 投稿失敗 - エラーパラメータ付きで戻る
			    response.sendRedirect("/miniApp/reviewList?mangaId=" + mangaId + "&error=1");
			}
			
		}
	}
}
