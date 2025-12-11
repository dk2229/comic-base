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

@WebServlet("/reviewEdit")
public class ReviewEditController extends HttpServlet {
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
		
		// セキュリティチェック：自分のレビューのみ編集可能
		if(review == null || review.getUserId() != user.getId()) {
			response.sendRedirect("/miniApp/mypage");
			return;
		}
		
		request.setAttribute("review", review);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewEdit.jsp");
		rd.forward(request, response);
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
		String name = request.getParameter("name");
		String evaluation = request.getParameter("evaluation");
		String impressions = request.getParameter("impressions");
		String number = request.getParameter("number");
		
		int reviewId = Integer.parseInt(reviewIdParam);
		
		// 元のレビュー情報を取得してセキュリティチェック
		ReviewService reviewService = new ReviewService();
		Review originalReview = reviewService.getReviewById(reviewId);
		
		if(originalReview == null || originalReview.getUserId() != user.getId()) {
			response.sendRedirect("/miniApp/mypage");
			return;
		}
		
		Validation validation = new Validation();
		validation.isBlank("漫画名", name);
		validation.isBlank("評価", evaluation);
		validation.isBlank("感想", impressions);
		validation.length("漫画名", name, 1, 255);
		validation.length("感想", impressions, 1, 1000);
		
		if(validation.hasErrorMsg()) {
			request.setAttribute("errorMsg", validation.getErrorMsgList());
			request.setAttribute("review", originalReview);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewEdit.jsp");
			rd.forward(request, response);
		} else {
			// 更新用レビューオブジェクト作成
			Review updatedReview = new Review(originalReview.getUserId(), originalReview.getMangaId(), name, evaluation, impressions, number);
			updatedReview.setReviewsId(reviewId);
			
			boolean result = reviewService.reviewEditDo(updatedReview);
			
			if(result == true) {
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewEditDone.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("editError", "レビュー情報の更新に失敗しました。");
				request.setAttribute("review", originalReview);
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewEdit.jsp");
				rd.forward(request, response);
			}
		}
	}
}