package com.com.com.dao;

import java.util.List;
import java.util.Map;

import com.com.com.domain.BoardVO;

public interface BoardDaoInter {

	public List<BoardVO> viewAll();
	
	public void write(BoardVO vo);
	
	public BoardVO detail(int seq);
	
	public void delete(int seq);

	int update(BoardVO vo);

	public BoardVO selectSEQ(int seq);

	public List<BoardVO> deleteList(Integer[] list);

	
	/*-------------------------------------------*/
	
	List<Map<String, Object>> boardList(Map<String, Object> map);
	
	int boardInsert(Map<String, Object> paramMap);
	
	Map<String, Object> boardDetail(int seq);
}
