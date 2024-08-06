package com.com.com.service.impl;

import java.util.List;
import java.util.Optional;

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
	public void insertBoard(BoardVO vo) {
		boardDao.boardWrite(vo);
	}

	@Override
	public BoardVO detail(int seq) {
		return boardDao.detail(seq);
	}

	@Override
	public void delete(int seq) {
		boardDao.delete(seq);
	}
	
	public void update(BoardVO vo) {
		boardDao.update(vo);
	}
}
