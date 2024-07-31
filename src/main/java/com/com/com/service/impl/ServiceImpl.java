package com.com.com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.com.dao.BoardDaoInter;
import com.com.com.domain.BoardVO;
import com.com.com.service.BoardService;

@Service
public class ServiceImpl implements BoardService {
	
	@Autowired
	private BoardDaoInter boardDao;
	
	@Override
	public List<BoardVO> getAllBoards() {
		return boardDao.viewAll();
	}
}
