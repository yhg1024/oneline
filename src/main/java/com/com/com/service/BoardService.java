package com.com.com.service;

import java.util.List;
import com.com.com.domain.BoardVO;

public interface BoardService {
		
	// 리스트 조회
	public List<BoardVO> getAllBoards();

	// 게시물 등록
	public void insertBoard(BoardVO board) throws Exception;

	public BoardVO detail(int seq);


	// 게시물 수정

}
