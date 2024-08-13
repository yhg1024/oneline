package com.com.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.com.com.domain.BoardVO;

public interface BoardService {
		
	public List<BoardVO> getAllBoards();

	public void insert(BoardVO vo);

	public BoardVO detail(int seq);

	public void delete(int seq);

	public void update(BoardVO vo);

	public void viewCnt(int seq);

	/*------------------------------------------------*/

	public List<Map<String, Object>> listMap(Map<String, Object> map);

	public int boardInsert(Map<String, Object> map);

	public Map<String, Object> boardDetail(int num);

	public void search(Model model, String type, String keyword);

	List<BoardVO> search(String searchType, String keyword, String startDate, String endDate) throws Exception;






}
