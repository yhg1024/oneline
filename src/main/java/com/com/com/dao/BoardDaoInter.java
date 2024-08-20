package com.com.com.dao;

import java.util.List;
import java.util.Map;

import com.com.com.domain.BoardVO;
import com.com.com.domain.PageVO;

public interface BoardDaoInter {

	public List<BoardVO> list(String searchType, String keyword, String startDate, String endDate, PageVO vo) throws Exception;
	
	public void write(BoardVO vo);
	
	public BoardVO detail(int seq);
	
	public void viewCnt(int seq);
	
	public void delete(int seq);

	public int update(BoardVO vo);
	
	// 페이지네이션
	public Integer totalCount(String searchType, String keyword, String startDate, String endDate, PageVO vo);
	
	
	/*-------------------------------------------*/
	
	List<Map<String, Object>> boardList(Map<String, Object> map);
	
	int boardInsert(Map<String, Object> paramMap);
	
	Map<String, Object> boardDetail(int seq);


	
}
