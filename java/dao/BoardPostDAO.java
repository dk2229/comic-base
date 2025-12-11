package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BoardPostDTO;


public class BoardPostDAO extends BaseDAO {
	
	// 特定漫画の掲示板投稿一覧取得
		public List<BoardPostDTO> selectByMangaId(int mangaId) {
			List<BoardPostDTO> postList = new ArrayList<>();
			
			Connection conn = getConnection();
			
			try {
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM board_posts WHERE manga_id = ? ORDER BY post_number ASC");
				ps.setInt(1, mangaId);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					BoardPostDTO dto = new BoardPostDTO();
					dto.setPostId(rs.getInt(1));
					dto.setMangaId(rs.getInt(2));
					dto.setPostNumber(rs.getInt(3));
					dto.setName(rs.getString(4));
					dto.setContent(rs.getString(5));
					dto.setUserId(rs.getObject(6, Integer.class));
					dto.setCreatedAt(rs.getTimestamp(7));
					postList.add(dto);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return postList;
		}

		// 次の投稿番号を取得
		public int getNextPostNumber(int mangaId) {
			int nextNumber = 1;
			
			Connection conn = getConnection();
			
			try {
				PreparedStatement ps = conn.prepareStatement("SELECT MAX(post_number) FROM board_posts WHERE manga_id = ?");
				ps.setInt(1, mangaId);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					Integer maxNumber = rs.getObject(1, Integer.class);
					if(maxNumber != null) {
						nextNumber = maxNumber + 1;
					}
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return nextNumber;
		}

		// 掲示板投稿登録
		public int insert(BoardPostDTO dto) {
			int result = 0;
			
			Connection conn = getConnection();
			
			TransactionManager tm = new TransactionManager(conn);
			
			try {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO board_posts(manga_id, post_number, name, content, user_id) VALUES(?,?,?,?,?)");
				ps.setInt(1, dto.getMangaId());
				ps.setInt(2, dto.getPostNumber());
				ps.setString(3, dto.getName());
				ps.setString(4, dto.getContent());
				if(dto.getUserId() != null) {
					ps.setInt(5, dto.getUserId());
				} else {
					ps.setNull(5, java.sql.Types.INTEGER);
				}
				
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

