package com.com.com.service;

import java.util.List;

import com.com.com.domain.BoardVO;

public interface BoardService {
		
	List<BoardVO> getAllBoards();

	int boardWriteService(BoardVO board) throws Exception;



}
