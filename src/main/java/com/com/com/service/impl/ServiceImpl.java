package com.com.com.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.com.com.dao.BoardDaoClass;
import com.com.com.domain.BoardVO;
import com.com.com.mapper.BoardMapper;
import com.com.com.service.BoardService;

import oracle.net.aso.b;

@Service
public class ServiceImpl implements BoardService {
	
	@Autowired
	private BoardDaoClass boardDao;
	
	@Override
	public List<BoardVO> getAllBoards() {
		return boardDao.viewAll();
	}

	@Override
	public void insert(BoardVO vo) {
		boardDao.write(vo);
	}

	@Override
	public BoardVO detail(int seq) {
		return boardDao.detail(seq);
	}

	@Override
	public void viewCnt(int seq) {
		// TODO Auto-generated method stub
		boardDao.viewCnt(seq);
	}
	
	@Override
	public void delete(int seq) {
		boardDao.delete(seq);
	}
	
	@Override
	public void update(BoardVO vo) {
		boardDao.update(vo);
	}
	
	// 검색
	@Override
	public List<BoardVO> search(String searchType, String keyword, String startDate, String endDate) throws Exception {
	     return  boardDao.search(searchType, keyword, startDate, endDate);
	}
	
	/*-------------------------------------------------------------------*/

	@Override
	public List<Map<String, Object>> listMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardDao.boardList(map);
	}

	@Override
	public int boardInsert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.boardInsert(paramMap);
	}

	@Override
	public Map<String, Object> boardDetail(int seq) {
		// TODO Auto-generated method stub
		return boardDao.boardDetail(seq);
	}

	@Override
	public void search(Model model, String type, String keyword) {
		// TODO Auto-generated method stub
		
	}

	


}
