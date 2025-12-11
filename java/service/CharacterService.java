package service;


import java.util.ArrayList;
import java.util.List;

import dao.CharacterDAO;
import domain.Character;
import dto.CharacterDTO;

public class CharacterService {
	
	// キャラクター登録処理
		public boolean characterRegisterDo(Character character) {
			CharacterDAO characterDAO = new CharacterDAO();
			CharacterDTO dto = new CharacterDTO(character.getMangaId(), character.getCharaName(), character.getCharaType(), character.getExplanation(), character.getFirst());
			
			int result = characterDAO.insert(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		// 特定漫画のキャラクター一覧取得処理
		public List<Character> getCharacterListByMangaId(int mangaId) {
			CharacterDAO characterDAO = new CharacterDAO();
			List<CharacterDTO> dtoList = characterDAO.selectByMangaId(mangaId);
			List<Character> characterList = new ArrayList<>();
			
			for(CharacterDTO dto : dtoList) {
				Character character = new Character(dto.getMangaId(), dto.getCharaName(), dto.getCharaType(), dto.getExplanation(), dto.getFirst());
				character.setCharaId(dto.getCharaId());
				character.setCreatedAt(dto.getCreatedAt());
				characterList.add(character);
			}
			
			return characterList;
		}
		
		// キャラクター詳細取得処理
		public Character getCharacterById(int charaId) {
			CharacterDAO characterDAO = new CharacterDAO();
			CharacterDTO dto = characterDAO.selectById(charaId);
			
			if(dto != null) {
				Character character = new Character(dto.getMangaId(), dto.getCharaName(), dto.getCharaType(), dto.getExplanation(), dto.getFirst());
				character.setCharaId(dto.getCharaId());
				character.setCreatedAt(dto.getCreatedAt());
				return character;
			} else {
				return null;
			}
		}
		
		// キャラクター情報更新処理
		public boolean characterEditDo(Character character) {
			CharacterDAO characterDAO = new CharacterDAO();
			CharacterDTO dto = new CharacterDTO(character.getMangaId(), character.getCharaName(), character.getCharaType(), character.getExplanation(), character.getFirst());
			dto.setCharaId(character.getCharaId());
			
			int result = characterDAO.edit(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
		
		// キャラクター削除処理
		public boolean characterDeleteDo(Character character) {
			CharacterDAO characterDAO = new CharacterDAO();
			CharacterDTO dto = new CharacterDTO();
			dto.setCharaId(character.getCharaId());
			
			int result = characterDAO.delete(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
