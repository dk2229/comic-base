package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CharacterDTO;

public class CharacterDAO extends BaseDAO {

	// 特定漫画のキャラクター一覧取得
	public List<CharacterDTO> selectByMangaId(int mangaId) {
		List<CharacterDTO> characterList = new ArrayList<>();
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM characters WHERE manga_id = ? ORDER BY created_at ASC");
			ps.setInt(1, mangaId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				CharacterDTO dto = new CharacterDTO();
				dto.setCharaId(rs.getInt(1));
				dto.setMangaId(rs.getInt(2));
				dto.setCharaName(rs.getString(3));
				dto.setCharaType(rs.getString(4));
				dto.setExplanation(rs.getString(5));
				dto.setFirst(rs.getString(6));
				dto.setCreatedAt(rs.getTimestamp(7));
				characterList.add(dto);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return characterList;
	}

	// キャラクター詳細取得（ID指定）
	public CharacterDTO selectById(int charaId) {
		CharacterDTO dto = null;
		
		Connection conn = getConnection();
		
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM characters WHERE chara_id = ?");
			ps.setInt(1, charaId);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				dto = new CharacterDTO();
				dto.setCharaId(rs.getInt(1));
				dto.setMangaId(rs.getInt(2));
				dto.setCharaName(rs.getString(3));
				dto.setCharaType(rs.getString(4));
				dto.setExplanation(rs.getString(5));
				dto.setFirst(rs.getString(6));
				dto.setCreatedAt(rs.getTimestamp(7));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}

	// キャラクター登録
	public int insert(CharacterDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO characters(manga_id, chara_name, chara_type, explanation, first) VALUES(?,?,?,?,?)");
			ps.setInt(1, dto.getMangaId());
			ps.setString(2, dto.getCharaName());
			ps.setString(3, dto.getCharaType());
			ps.setString(4, dto.getExplanation());
			ps.setString(5, dto.getFirst());
			
			result = ps.executeUpdate();
			tm.commit();
			
		} catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	// キャラクター情報更新
	public int edit(CharacterDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE characters SET chara_name = ?, chara_type = ?, explanation = ?, first = ? WHERE chara_id = ?");
			ps.setString(1, dto.getCharaName());
			ps.setString(2, dto.getCharaType());
			ps.setString(3, dto.getExplanation());
			ps.setString(4, dto.getFirst());
			ps.setInt(5, dto.getCharaId());
			
			result = ps.executeUpdate();
			tm.commit();
		} catch(SQLException e) {
			tm.rollback();
			e.printStackTrace();
		}
		tm.close();
		return result;
	}
	
	// キャラクター削除
	public int delete(CharacterDTO dto) {
		int result = 0;
		
		Connection conn = getConnection();
		
		TransactionManager tm = new TransactionManager(conn);
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM characters WHERE chara_id = ?");
			ps.setInt(1, dto.getCharaId());
			
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
