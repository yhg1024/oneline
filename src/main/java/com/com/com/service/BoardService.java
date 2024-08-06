package com.com.com.service;

import java.util.List;
import java.util.Map;

import com.com.com.domain.BoardVO;

public interface BoardService {
		
	public List<BoardVO> getAllBoards();

	public void insertBoard(BoardVO vo);

	public BoardVO detail(int seq);

	public void delete(int seq);

	public void update(BoardVO vo);


}
