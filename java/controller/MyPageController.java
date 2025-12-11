package controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/mypage")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(user == null) {
			// ログインしていない場合はログイン画面へ
			response.sendRedirect("/miniApp/login");
			return;
		}
		
		// ユーザーのレビュー一覧を取得
		ReviewService reviewService = new ReviewService();
		List<Review> userReviews = reviewService.getReviewListByUserId(user.getId());
		
		request.setAttribute("userReviews", userReviews);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/mypage.jsp");
		rd.forward(request, response);
	}
}

