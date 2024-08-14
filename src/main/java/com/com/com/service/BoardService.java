package com.com.com.service;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

import com.com.com.domain.BoardVO;
import com.com.com.domain.PageVO;

public interface BoardService {
		
	public List<BoardVO> getAllBoards();

	public void insert(BoardVO vo);

	public BoardVO detail(int seq);

	public void delete(int seq);

	public void update(BoardVO vo);

	public void viewCnt(int seq);

	// 검색
	List<BoardVO> search(String searchType, String keyword, String startDate, String endDate) throws Exception;
	
	// 게시물 총 갯수
	public Integer totalCount();
	
	// 페이징 처리 게시글 조회
	public List<BoardVO> pagination(PageVO vo);
	
	/*------------------------------------------------*/

	public List<Map<String, Object>> listMap(Map<String, Object> map);

	public int boardInsert(Map<String, Object> map);

	public Map<String, Object> boardDetail(int num);









}
