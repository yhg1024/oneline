package com.com.com.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.com.dao.BoardDaoClass;
import com.com.com.domain.BoardVO;
import com.com.com.mapper.BoardMapper;
import com.com.com.service.BoardService;

@Service
public class ServiceImpl implements BoardService {
	
	@Autowired
	private BoardDaoClass boardDao;
	BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getAllBoards() {
		return boardDao.viewAll();
	}

	@Override
	public int boardWriteService(BoardVO vo) throws Exception {
		// TODO Auto-generated method stub
		return boardDao.boardWrite(vo);
	}
}
