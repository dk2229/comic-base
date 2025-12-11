package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.MangaDTO;
	
	public class MangaDAO extends BaseDAO {

		// 全漫画一覧取得
		public List<MangaDTO> selectAll() {
			List<MangaDTO> mangaList = new ArrayList<>();
			
			Connection conn = getConnection();
			
			try {
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM mangas ORDER BY created_at DESC");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					MangaDTO dto = new MangaDTO();
					dto.setMangaId(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setAuthor(rs.getString(3));
					dto.setNumber(rs.getString(4));
					dto.setPublisher(rs.getString(5));
					dto.setGenre(rs.getString(6));
					dto.setCreatedAt(rs.getTimestamp(7));
					mangaList.add(dto);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return mangaList;
		}

		// 漫画詳細取得（ID指定）
		public MangaDTO selectById(int mangaId) {
			MangaDTO dto = null;
			
			Connection conn = getConnection();
			
			try {
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM mangas WHERE manga_id = ?");
				ps.setInt(1, mangaId);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					dto = new MangaDTO();
					dto.setMangaId(rs.getInt(1));
					dto.setTitle(rs.getString(2));
					dto.setAuthor(rs.getString(3));
					dto.setNumber(rs.getString(4));
					dto.setPublisher(rs.getString(5));
					dto.setGenre(rs.getString(6));
					dto.setCreatedAt(rs.getTimestamp(7));
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return dto;
		}

		// 漫画登録
		public int insert(MangaDTO dto) {
			int result = 0;
			
			Connection conn = getConnection();
			
			TransactionManager tm = new TransactionManager(conn);
			
			try {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO mangas(title, author, number, publisher, genre) VALUES(?,?,?,?,?)");
				ps.setString(1, dto.getTitle());
				ps.setString(2, dto.getAuthor());
				ps.setString(3, dto.getNumber());
				ps.setString(4, dto.getPublisher());
				ps.setString(5, dto.getGenre());
				
				result = ps.executeUpdate();
				tm.commit();
				
			} catch(SQLException e) {
				tm.rollback();
				e.printStackTrace();
			}
			tm.close();
			return result;
		}
		
		// 漫画情報更新
		public int edit(MangaDTO dto) {
			int result = 0;
			
			Connection conn = getConnection();
			
			TransactionManager tm = new TransactionManager(conn);
			
			try {
				PreparedStatement ps = conn.prepareStatement("UPDATE mangas SET title = ?, author = ?, number = ?, publisher = ?, genre = ? WHERE manga_id = ?");
				ps.setString(1, dto.getTitle());
				ps.setString(2, dto.getAuthor());
				ps.setString(3, dto.getNumber());
				ps.setString(4, dto.getPublisher());
				ps.setString(5, dto.getGenre());
				ps.setInt(6, dto.getMangaId());
				
				result = ps.executeUpdate();
				tm.commit();
			} catch(SQLException e) {
				tm.rollback();
				e.printStackTrace();
			}
			tm.close();
			return result;
		}
		
		// 漫画削除
		public int delete(MangaDTO dto) {
			int result = 0;
			
			Connection conn = getConnection();
			
			TransactionManager tm = new TransactionManager(conn);
			
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM mangas WHERE manga_id = ?");
				ps.setInt(1, dto.getMangaId());
				
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
