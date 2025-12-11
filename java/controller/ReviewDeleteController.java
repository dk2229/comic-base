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

@WebServlet("/reviewDelete")
public class ReviewDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// GETリクエストの場合は削除確認画面にリダイレクト
		String reviewId = request.getParameter("reviewId");
		if(reviewId != null) {
			response.sendRedirect("/miniApp/reviewDeleteConfirm?reviewId=" + reviewId);
		} else {
			response.sendRedirect("/miniApp/mypage");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			response.sendRedirect("/miniApp/login");
			return;
		}
		
		String reviewIdParam = request.getParameter("reviewId");
		int reviewId = Integer.parseInt(reviewIdParam);
		
		ReviewService reviewService = new ReviewService();
		Review review = reviewService.getReviewById(reviewId);
		
		// セキュリティチェック：自分のレビューのみ削除可能
		if(review == null || review.getUserId() != user.getId()) {
			response.sendRedirect("/miniApp/mypage");
			return;
		}
		
		// 削除実行
		boolean result = reviewService.reviewDeleteDo(review);
		
		if(result == true) {
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewDeleteDone.jsp");
			rd.forward(request, response);
		} else {
			// 削除失敗時はマイページに戻る
			response.sendRedirect("/miniApp/mypage");
		}
	}
}