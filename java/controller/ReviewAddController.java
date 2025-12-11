package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Review;
import domain.User;
import service.ReviewService;
import validation.Validation;


@WebServlet("/reviewAdd")
public class ReviewAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mangaIdParam = request.getParameter("mangaId");
		request.setAttribute("mangaId", mangaIdParam);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewAdd.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String mangaIdParam = request.getParameter("mangaId");
		String name = request.getParameter("name");
		String evaluation = request.getParameter("evaluation");
		String impressions = request.getParameter("impressions");
		String number = request.getParameter("number");
		
		int mangaId = Integer.parseInt(mangaIdParam);
		
		// セッションからユーザー情報取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			// ログインしていない場合はログイン画面へ
			RequestDispatcher rd = request.getRequestDispatcher("/login");
			rd.forward(request, response);
			return;
		}
		
		Validation validation = new Validation();
		validation.isBlank("お名前", name);
		validation.isBlank("評価", evaluation);
		validation.isBlank("感想", impressions);
		validation.length("お名前", name, 1, 255);
		validation.length("感想", impressions, 1, 1000);
		
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			request.setAttribute("mangaId", mangaIdParam);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewAdd.jsp");
			rd.forward(request, response);
		} else {
			Review review = new Review(user.getId(), mangaId, name, evaluation, impressions, number);
			
			ReviewService reviewService = new ReviewService();
			boolean result = reviewService.reviewRegisterDo(review);
			
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewDone.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("registerError", "レビューの投稿に失敗しました。");
				request.setAttribute("mangaId", mangaIdParam);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewAdd.jsp");
				rd.forward(request, response);
			}
		}
	}
}
