package com.com.com.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.com.com.domain.BoardVO;

@Repository("dao")
public class BoardDaoClass implements BoardDaoInter{
	
	@Inject
	public SqlSessionTemplate sqlSession;

	@Override
	public List<BoardVO> viewAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.viewAll");
	}

	public int boardWrite(BoardVO vo) { 
		return sqlSession.insert("mapper.write", vo);
	} 

	
}
