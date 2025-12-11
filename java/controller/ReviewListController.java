package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardPost;
import domain.Manga;
import domain.Review;
import service.BoardPostService;
import service.MangaService;
import service.ReviewService;


@WebServlet("/reviewList")
public class ReviewListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String mangaIdParam = request.getParameter("mangaId");
		String replyToParam = request.getParameter("replyTo"); 
		
		if(mangaIdParam == null || mangaIdParam.trim().isEmpty()) {
	        response.sendRedirect("/miniApp/mangaList?error=1");
	        return;
	    }

		int mangaId = Integer.parseInt(mangaIdParam);
		
		
		
		// 漫画情報取得
		MangaService mangaService = new MangaService();
		Manga manga = mangaService.getMangaById(mangaId);
		
		// レビュー一覧取得
		ReviewService reviewService = new ReviewService();
		List<Review> reviewList = reviewService.getReviewListByMangaId(mangaId);
		

		// 掲示板投稿一覧取得（新規追加）
		BoardPostService boardPostService = new BoardPostService();
		List<BoardPost> boardPostList = boardPostService.getBoardPostListByMangaId(mangaId);
		
		if(replyToParam != null && !replyToParam.isEmpty()) {       
	        request.setAttribute("replyTo", replyToParam);            
	        }
		
		request.setAttribute("manga", manga);
		request.setAttribute("reviewList", reviewList);
		request.setAttribute("boardPostList", boardPostList);  // 新規追加
		
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/reviewList.jsp");
		rd.forward(request, response);
	}
}

