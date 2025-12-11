package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ReviewDTO;

public class ReviewDAO extends BaseDAO {

	// 特定漫画のレビュー一覧取得
	public List<ReviewDTO> selectByMangaId(int mangaId) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reviews WHERE manga_id = ? ORDER BY created_at DESC");
			ps.setInt(1, mangaId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReviewsId(rs.getInt(1));
				dto.setUserId(rs.getInt(2));
				dto.setMangaId(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setEvaluation(rs.getString(5));
				dto.setImpressions(rs.getString(6));
				dto.setNumber(rs.getString(7));
				dto.setCreatedAt(rs.getTimestamp(8));
				reviewList.add(dto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return reviewList;
	}

	// 特定ユーザーのレビュー一覧取得
	public List<ReviewDTO> selectByUserId(int userId) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reviews WHERE user_id = ? ORDER BY created_at DESC");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setReviewsId(rs.getInt(1));
				dto.setUserId(rs.getInt(2));
				dto.setMangaId(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setEvaluation(rs.getString(5));
				dto.setImpressions(rs.getString(6));
				dto.setNumber(rs.getString(7));
				dto.setCreatedAt(rs.getTimestamp(8));
				reviewList.add(dto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return reviewList;
	}

	// レビュー詳細取得（ID指定）
	public ReviewDTO selectById(int reviewsId) {
		ReviewDTO dto = null;
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM reviews WHERE reviews_id = ?");
			ps.setInt(1, reviewsId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new ReviewDTO();
				dto.setReviewsId(rs.getInt(1));
				dto.setUserId(rs.getInt(2));
				dto.setMangaId(rs.getInt(3));
				dto.setName(rs.getString(4));
				dto.setEvaluation(rs.getString(5));
				dto.setImpressions(rs.getString(6));
				dto.setNumber(rs.getString(7));
				dto.setCreatedAt(rs.getTimestamp(8));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	// レビュー登録
	public int insert(ReviewDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO reviews(user_id, manga_id, name, evaluation, impressions, number) VALUES(?,?,?,?,?,?)");
			ps.setInt(1, dto.getUserId());
			ps.setInt(2, dto.getMangaId());
			ps.setString(3, dto.getName());
			ps.setString(4, dto.getEvaluation());
			ps.setString(5, dto.getImpressions());
			ps.setString(6, dto.getNumber());
			
			result = ps.executeUpdate();
			tm.commit();
			
		} catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	// レビュー情報更新
	public int edit(ReviewDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE reviews SET name = ?, evaluation = ?, impressions = ?, number = ? WHERE reviews_id = ?");
			ps.setString(1, dto.getName());
			ps.setString(2, dto.getEvaluation());
			ps.setString(3, dto.getImpressions());
			ps.setString(4, dto.getNumber());
			ps.setInt(5, dto.getReviewsId());
			
			result = ps.executeUpdate();
			tm.commit();
		} catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	// レビュー削除
	public int delete(ReviewDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM reviews WHERE reviews_id = ?");
			ps.setInt(1, dto.getReviewsId());
			
			result = ps.executeUpdate();
			tm.commit();
		} catch (SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
}
