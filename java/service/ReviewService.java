package service;

import java.util.ArrayList;
import java.util.List;

import dao.ReviewDAO;
import domain.Review;
import dto.ReviewDTO;


public class ReviewService {
	
	// レビュー登録処理
		public boolean reviewRegisterDo(Review review) {
			ReviewDAO reviewDAO = new ReviewDAO();
			ReviewDTO dto = new ReviewDTO(review.getUserId(), review.getMangaId(), review.getName(), review.getEvaluation(), review.getImpressions(), review.getNumber());
			
			int result = reviewDAO.insert(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		// 特定漫画のレビュー一覧取得処理
		public List<Review> getReviewListByMangaId(int mangaId) {
			ReviewDAO reviewDAO = new ReviewDAO();
			List<ReviewDTO> dtoList = reviewDAO.selectByMangaId(mangaId);
			List<Review> reviewList = new ArrayList<>();
			
			for(ReviewDTO dto : dtoList) {
				Review review = new Review(dto.getUserId(), dto.getMangaId(), dto.getName(), dto.getEvaluation(), dto.getImpressions(), dto.getNumber());
				review.setReviewsId(dto.getReviewsId());
				review.setCreatedAt(dto.getCreatedAt());
				reviewList.add(review);
			}
			
			return reviewList;
		}
		
		// 特定ユーザーのレビュー一覧取得処理
		public List<Review> getReviewListByUserId(int userId) {
			ReviewDAO reviewDAO = new ReviewDAO();
			List<ReviewDTO> dtoList = reviewDAO.selectByUserId(userId);
			List<Review> reviewList = new ArrayList<>();
			
			for(ReviewDTO dto : dtoList) {
				Review review = new Review(dto.getUserId(), dto.getMangaId(), dto.getName(), dto.getEvaluation(), dto.getImpressions(), dto.getNumber());
				review.setReviewsId(dto.getReviewsId());
				review.setCreatedAt(dto.getCreatedAt());
				reviewList.add(review);
			}
			
			return reviewList;
		}
		
		// レビュー詳細取得処理
		public Review getReviewById(int reviewsId) {
			ReviewDAO reviewDAO = new ReviewDAO();
			ReviewDTO dto = reviewDAO.selectById(reviewsId);
			
			if(dto != null) {
				Review review = new Review(dto.getUserId(), dto.getMangaId(), dto.getName(), dto.getEvaluation(), dto.getImpressions(), dto.getNumber());
				review.setReviewsId(dto.getReviewsId());
				review.setCreatedAt(dto.getCreatedAt());
				return review;
			} else {
				return null;
			}
		}
		
		// レビュー情報更新処理
		public boolean reviewEditDo(Review review) {
			ReviewDAO reviewDAO = new ReviewDAO();
			ReviewDTO dto = new ReviewDTO(review.getUserId(), review.getMangaId(), review.getName(), review.getEvaluation(), review.getImpressions(), review.getNumber());
			dto.setReviewsId(review.getReviewsId());
			
			int result = reviewDAO.edit(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		// レビュー削除処理
		public boolean reviewDeleteDo(Review review) {
			ReviewDAO reviewDAO = new ReviewDAO();
			ReviewDTO dto = new ReviewDTO();
			dto.setReviewsId(review.getReviewsId());
			
			int result = reviewDAO.delete(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
