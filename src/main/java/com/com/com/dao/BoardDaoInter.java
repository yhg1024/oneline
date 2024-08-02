package com.com.com.dao;

import java.util.List;
import java.util.Map;

import com.com.com.domain.BoardVO;

public interface BoardDaoInter {

	public List<BoardVO> viewAll();
	
	public int boardWrite(BoardVO vo);
	
	public BoardVO detail(int seq);

}
