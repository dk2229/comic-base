package service;


import java.util.ArrayList;
import java.util.List;

import dao.MangaDAO;
import domain.Manga;
import dto.MangaDTO;


public class MangaService {
	
	public boolean mangaRegisterDo(Manga manga) {
		MangaDAO mangaDAO = new MangaDAO();
		MangaDTO dto = new MangaDTO(manga.getTitle(), manga.getAuthor(), manga.getNumber(), manga.getPublisher(), manga.getGenre());
		
		int result = mangaDAO.insert(dto);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// 漫画一覧取得処理
	public List<Manga> getMangaList() {
		MangaDAO mangaDAO = new MangaDAO();
		List<MangaDTO> dtoList = mangaDAO.selectAll();
		List<Manga> mangaList = new ArrayList<>();
		
		for(MangaDTO dto : dtoList) {
			Manga manga = new Manga(dto.getTitle(), dto.getAuthor(), dto.getNumber(), dto.getPublisher(), dto.getGenre());
			manga.setMangaId(dto.getMangaId());
			manga.setCreatedAt(dto.getCreatedAt());
			mangaList.add(manga);
		}
		
		return mangaList;
	}
	
	// 漫画詳細取得処理
	public Manga getMangaById(int mangaId) {
		MangaDAO mangaDAO = new MangaDAO();
		MangaDTO dto = mangaDAO.selectById(mangaId);
		
		if(dto != null) {
			Manga manga = new Manga(dto.getTitle(), dto.getAuthor(), dto.getNumber(), dto.getPublisher(), dto.getGenre());
			manga.setMangaId(dto.getMangaId());
			manga.setCreatedAt(dto.getCreatedAt());
			return manga;
		} else {
			return null;
		}
	}
	
	// 漫画情報更新処理
	public boolean mangaEditDo(Manga manga) {
		MangaDAO mangaDAO = new MangaDAO();
		MangaDTO dto = new MangaDTO(manga.getTitle(), manga.getAuthor(), manga.getNumber(), manga.getPublisher(), manga.getGenre());
		dto.setMangaId(manga.getMangaId());
		
		int result = mangaDAO.edit(dto);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	// 漫画削除処理
	public boolean mangaDeleteDo(Manga manga) {
		MangaDAO mangaDAO = new MangaDAO();
		MangaDTO dto = new MangaDTO();
		dto.setMangaId(manga.getMangaId());
		
		int result = mangaDAO.delete(dto);
		if(result == 1) {
			return true;
		} else {
			return false;
		}
	}
}

