package com.com.com.dao;

import java.util.List;
import java.util.Map;

import com.com.com.domain.BoardVO;
import com.com.com.domain.PageVO;

public interface BoardDaoInter {
	
	List<Map<String, Object>> list(Map<String, Object> map, PageVO vo);
	
	int insert(Map<String, Object> paramMap);
	
	public BoardVO detail(int num);
	
	public void viewCnt(int seq);
	
	public void delete(int seq);

	public int update(BoardVO vo);
	
	// 페이지네이션
	public Integer totalCount(Map<String, Object> map, PageVO vo);
	
	
	/*-------------------------------------------*/


	
}
