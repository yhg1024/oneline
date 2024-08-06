package com.com.com.dao;

import java.util.List;
import java.util.Optional;

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

	@Override
	public void boardWrite(BoardVO vo) { 
		sqlSession.insert("mapper.write", vo); // , 뒤에는 여러개를 못써서 vo에 여러개의 데이터를 넣고 뺴고 쓴다
	}

	@Override
	public BoardVO detail(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.detail", seq);
	}

	public void delete(int seq) {
		sqlSession.delete("mapper.delete", seq);
		
	}

	public BoardVO selectSEQ(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("map.select", seq);
	}

	public void update(BoardVO vo) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.update", vo);
	} 

	public List<BoardVO> deleteList(String[] list) {
		return sqlSession.selectList("deleteList", list);
	}
	
}
