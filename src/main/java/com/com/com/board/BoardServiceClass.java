package com.com.com.board;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service("service")
public class BoardServiceClass implements BoardServiceInter{
	
	@Inject
	public BoardDaoInter dao;

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		
		return dao.list(map);
	}

	@Override
	public int insert(Map<String, Object> map) {
		return dao.insert(map);
	}
	
	@Override
	public void file(Map<String, Object> map) {
		dao.file(map);
	}

	@Override
	public Map<String, Object> detail(int seq) {
		
		return dao.detail(seq);
	}

	@Override
	public void delete(int seq) {
		// TODO Auto-generated method stub
		dao.delete(seq);
	}

	@Override
	public int update(Map<String, Object> map) {
		return dao.update(map);
	}

	@Override
	public void viewCnt(int seq) {
		dao.viewCnt(seq);		
	}

	@Override
	public int totalCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.totalCount(map);
	}

	@Override
	public List<Map<String, Object>> selectFileList(int seq) {
		// TODO Auto-generated method stub
		return dao.selectFileList(seq);
	}

	@Override
	public Map<String, Object> selectFileInfo(int seq) {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(seq);
	}
	
	

}
