package com.com.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.com.com.domain.BoardVO;
import com.com.com.domain.PageVO;

public interface BoardService {
		
	public List<Map<String, Object>> list(Map<String, Object> map, PageVO vo);

	public int insert(Map<String, Object> map);

	public BoardVO detail(int seq);

	public void delete(int seq);

	public void update(BoardVO vo);

	public void viewCnt(int seq);
	
	// 게시물 총 갯수
	public Integer totalCount(Map<String, Object> map, PageVO vo);
	
	/*------------------------------------------------*/

	public int delete(Integer[] chk);










}
