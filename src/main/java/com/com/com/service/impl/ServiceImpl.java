package com.com.com.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.com.com.dao.BoardDaoClass;
import com.com.com.domain.BoardVO;
import com.com.com.domain.PageVO;
import com.com.com.mapper.BoardMapper;
import com.com.com.service.BoardService;

import oracle.net.aso.b;

@Service
public class ServiceImpl implements BoardService {
	
	@Autowired
	private BoardDaoClass boardDao;
	
	@Override
	public List<Map<String, Object>> list(Map<String, Object> map, PageVO vo) {
		System.out.println("service =" + map);
		return boardDao.list(map, vo);
	}

	@Override
	public int insert(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return boardDao.insert(paramMap);
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
	
	
	@Override
	public Integer totalCount(Map<String, Object> map, PageVO vo) {
		return boardDao.totalCount(map, vo);
	}

	/*-------------------------------------------------------------------*/


	@Override
	public int delete(Integer[] chk) {
		// TODO Auto-generated method stub
		return boardDao.delete(chk);
	}
	


	

	


}
