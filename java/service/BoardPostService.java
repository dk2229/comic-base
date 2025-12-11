package service;

import java.util.ArrayList;
import java.util.List;

import dao.BoardPostDAO;
import domain.BoardPost;
import dto.BoardPostDTO;


public class BoardPostService {
	
	// 特定漫画の掲示板投稿一覧取得処理
		public List<BoardPost> getBoardPostListByMangaId(int mangaId) {
			BoardPostDAO boardPostDAO = new BoardPostDAO();
			List<BoardPostDTO> dtoList = boardPostDAO.selectByMangaId(mangaId);
			List<BoardPost> boardPostList = new ArrayList<>();
			
			for(BoardPostDTO dto : dtoList) {
				BoardPost boardPost = new BoardPost(dto.getMangaId(), dto.getPostNumber(), dto.getName(), dto.getContent(), dto.getUserId());
				boardPost.setPostId(dto.getPostId());
				boardPost.setCreatedAt(dto.getCreatedAt());
				boardPostList.add(boardPost);
			}
			
			return boardPostList;
		}
		
		// 掲示板投稿処理
		public boolean boardPostRegisterDo(BoardPost boardPost) {
			BoardPostDAO boardPostDAO = new BoardPostDAO();
			
			// 次の投稿番号を取得
			int nextPostNumber = boardPostDAO.getNextPostNumber(boardPost.getMangaId());
			
			BoardPostDTO dto = new BoardPostDTO(boardPost.getMangaId(), nextPostNumber, boardPost.getName(), boardPost.getContent(), boardPost.getUserId());
			
			int result = boardPostDAO.insert(dto);
			if(result == 1) {
				return true;
			} else {
				return false;
			}
		}
	}

