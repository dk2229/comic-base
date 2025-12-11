package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.QuizQuestionDTO;

public class QuizQuestionDAO extends BaseDAO {

	public List<QuizQuestionDTO> selectRandomQuestions(int mangaId, int questionCount) {
		List<QuizQuestionDTO> dtoList = new ArrayList<>();
		
		Connection conn = getConnection();
		
		try {PreparedStatement ps = conn.prepareStatement("SELECT * FROM quiz_questions WHERE manga_id = ? ORDER BY RANDOM() LIMIT ?");
			ps.setInt(1, mangaId);
			ps.setInt(2, questionCount);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				QuizQuestionDTO dto = new QuizQuestionDTO();
				dto.setQuestionId(rs.getInt(1));
				dto.setMangaId(rs.getInt(2));
				dto.setKanjiText(rs.getString(3));
				dto.setCorrectAnswer(rs.getString(4));
				dto.setHint(rs.getString(5));
				dto.setDifficulty(rs.getInt(6));
				dto.setCreatedAt(rs.getTimestamp(7));
				dtoList.add(dto);
			}
			
			}catch(SQLException e) {
			e.printStackTrace();
			}
			return dtoList;
	}

	public int getTotalQuestions(int mangaId) {
		int count = 0;
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement(
				"SELECT COUNT(*) FROM quiz_questions WHERE manga_id = ?");
			ps.setInt(1, mangaId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public boolean isQuestionText(String text, int mangaId) {
		boolean result = false;
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM quiz_questions WHERE manga_id = ? AND kanji_text = ?");
			ps.setInt(1, mangaId);
			ps.setString(2, text);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count > 0) {
					result = true;
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}


	public int insert(QuizQuestionDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO quiz_questions(manga_id, kanji_text, correct_answer, hint, difficulty) VALUES(?,?,?,?,?)");
			ps.setInt(1, dto.getMangaId());
			ps.setString(2, dto.getKanjiText());
			ps.setString(3, dto.getCorrectAnswer());
			ps.setString(4, dto.getHint());
			ps.setInt(5, dto.getDifficulty());
			
			result = ps.executeUpdate();
			tm.commit();
			
		} catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
}