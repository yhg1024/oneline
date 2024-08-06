package com.com.com.service;

import java.util.List;
import java.util.Map;

import com.com.com.domain.BoardVO;

public interface BoardService {
		
	public List<BoardVO> getAllBoards();

	public void insert(BoardVO vo);

	public BoardVO detail(int seq);

	public void delete(int seq);

	public void update(BoardVO vo);

	public List<BoardVO> delete(Integer[] list);
	
	/*------------------------------------------------*/

	public List<Map<String, Object>> listMap(Map<String, Object> map);

	public int boardInsert(Map<String, Object> map);

	public Map<String, Object> boardDetail(int num);


}
