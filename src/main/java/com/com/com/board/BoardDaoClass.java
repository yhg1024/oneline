package com.com.com.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

@Repository("dao")
public class BoardDaoClass implements BoardDaoInter {
	
	@Inject
	public SqlSessionTemplate sqlSession;
	

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map) {
		HashMap<String, Object> data = new HashMap<String, Object>(map); // map의 내용을 data에 복사
	    
	    data.put("searchType", map.getOrDefault("searchType", ""));
		data.put("endDate", map.getOrDefault("endDate", "")); 
		data.put("startDate", map.getOrDefault("startDate", "")); 
		data.put("keyword",	map.getOrDefault("keyword", ""));	    
		
		data.put("start", map.getOrDefault("start", ""));
	    data.put("end", map.getOrDefault("end", "")); 
	    
		
		/*
		 * for (String key : map.keySet()) { System.out.println("Key: " + key +
		 * ", Value: " + map.get(key)); }
		 */
		 
	    
		return sqlSession.selectList("mapper.list", data);
	}

	@Override
	public int insert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mapper.insert", map);
	}
	
	@Override
	public void file(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlSession.insert("mapper.uploadFile", map);
	}
	
	@Override
	public Map<String, Object> detail(int seq) {
		return sqlSession.selectOne("mapper.detail", seq);
	}

	@Override
	public void delete(int seq) {
		sqlSession.delete("mapper.delete", seq);		
	}

	@Override
	public int update(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.update("mapper.update", map);
	}

	@Override
	public void viewCnt(int seq) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.viewCnt", seq);
	}

	@Override
	public int totalCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.totalCount", map);
	}

	@Override
	public List<Map<String, Object>> selectFileList(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.selectFileList", seq);
	}

	@Override
	public Map<String, Object> selectFileInfo(int seq) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.selectFileInfo", seq);
	}

	

}
