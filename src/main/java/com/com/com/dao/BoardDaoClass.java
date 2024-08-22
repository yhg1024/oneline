package com.com.com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.com.com.domain.BoardVO;
import com.com.com.domain.PageVO;

@Repository("dao")
public class BoardDaoClass implements BoardDaoInter{
	
	@Inject
	public SqlSessionTemplate sqlSession;
	// DAO클래스에 직접 SqlSession객체를선언하고 @Autowired로 의존주입하여 쿼리문을 수행 하는 방식
	
	// List selectList(query_id)	id에 대한 select문을 실행한 후 레코드를 List로 반환합니다.
	// List selectList(query_id, '조건')	id에 대한 select문을 실행하면서 조건(쿼리문에서 사용할 인자)를 전달합니다.
	// T selectOne(query_id)	id에 대한 select문을 실행한 후 한개의 레코드를 지정한 타입으로 반환합니다.
	// T selectOne(query_id, '조건')	id에 대한 select문을 실행하면서 조건(쿼리문에서 사용할 인자)를 전달합니다. 

	@Override
	public List<Map<String, Object>> list(Map<String, Object> map, PageVO vo) {
		
	    HashMap<String, Object> data = new HashMap<String, Object>(map); // map의 내용을 data에 복사
	    
	    data.put("searchType", map.getOrDefault("searchType", ""));
		data.put("endDate", map.getOrDefault("endDate", "")); 
		data.put("startDate", map.getOrDefault("startDate", "")); 
		data.put("keyword",	map.getOrDefault("keyword", ""));

	    // 필요한 값 추가
	    data.put("start", vo.getStart());
	    data.put("end", vo.getEnd());

	    return sqlSession.selectList("mapper.list", data);
	}

	public int insert(Map<String, Object> map) {
		return sqlSession.insert("mapper.write", map);
	}

	@Override
	public BoardVO detail(int seq) {
		return sqlSession.selectOne("mapper.detail", seq);
	}

	@Override
	public void viewCnt(int seq) {
		sqlSession.update("mapper.viewCnt", seq);
	}
	
	@Override
	public void delete(int seq) {
		sqlSession.delete("mapper.delete", seq);		
	}
	
	@Override
	public int update(BoardVO vo) {
		return sqlSession.update("mapper.update", vo);
	} 
	
	// 페이지네이션
	@Override
	public Integer totalCount(Map<String, Object> map, PageVO vo) {
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("searchType", map.getOrDefault("searchType", ""));
		data.put("endDate", map.getOrDefault("endDate", "")); 
		data.put("startDate", map.getOrDefault("startDate", "")); 
		data.put("keyword",	map.getOrDefault("keyword", ""));
	    
		return sqlSession.selectOne("mapper.totalCount", data);
	}
	
	/*--------------------------------------------------------------*/

	public int delete(Integer[] chk) {
		// TODO Auto-generated method stub
		return sqlSession.delete("mapper.delete", chk);
	}
}
