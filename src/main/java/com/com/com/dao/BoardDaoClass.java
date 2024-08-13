package com.com.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

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
	public void write(BoardVO vo) { 
		sqlSession.insert("mapper.write", vo); // , 뒤에는 여러개를 못써서 vo에 여러개의 데이터를 넣고 뺴고 쓴다
	}

	@Override
	public BoardVO detail(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.detail", seq);
	}

	@Override
	public void viewCnt(int seq) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.viewCnt", seq);
	}
	
	@Override
	public void delete(int seq) {
		sqlSession.delete("mapper.delete", seq);		
	}
	
	@Override
	public int update(BoardVO vo) {
		// TODO Auto-generated method stub
		return sqlSession.update("mapper.update", vo);
	} 
	
	// 검색
	@Override
	public List<BoardVO> search(String searchType, String keyword, String startDate, String endDate) throws Exception {

		HashMap<String, Object> data = new HashMap<String, Object>();
	    data.put("searchType", searchType);
	    data.put("keyword", keyword);
	    data.put("startDate", startDate);
	    data.put("endDate", endDate);

	    return sqlSession.selectList("mapper.search", data);
	}
	/*--------------------------------------------------------------*/

	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.viewAll");
	}

	public int boardInsert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mapper.insert", map);
	}

	public Map<String, Object> boardDetail(int num) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.detail", num);
	}

	public void search(Model model, String type, String keyword) {
		// TODO Auto-generated method stub
		sqlSession.selectList("mapper.search");
	}


	

}
