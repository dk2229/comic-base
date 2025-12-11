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

@WebServlet("/reviewDeleteConfirm")
public class ReviewDeleteConfirmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
		
		request.setAttribute("review", review);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewDeleteConfirm.jsp");
		rd.forward(request, response);
	}
}
