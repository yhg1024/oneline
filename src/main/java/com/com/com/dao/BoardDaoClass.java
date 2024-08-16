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
	public List<BoardVO> list(String searchType, String keyword, String startDate, String endDate, PageVO vo) throws Exception{

		HashMap<String, Object> data = new HashMap<String, Object>();
	    data.put("searchType", searchType);
	    data.put("keyword", keyword);
	    data.put("startDate", startDate);
	    data.put("endDate", endDate);
	    data.put("start", vo.getStart());
	    data.put("end", vo.getEnd());
	    
	    System.out.println("BoardDaoClass = " + data);
	    
		return sqlSession.selectList("mapper.list", data);
	}

	@Override
	public void write(BoardVO vo) { 
		sqlSession.insert("mapper.write", vo); // , 뒤에는 여러개를 못써서 vo에 여러개의 데이터를 넣고 뺴고 쓴다
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
	public Integer totalCount() {
		return sqlSession.selectOne("mapper.totalCount");
	}
	
	/*--------------------------------------------------------------*/

	public List<Map<String, Object>> boardList(Map<String, Object> map) {
		return sqlSession.selectList("mapper.viewAll");
	}

	public int boardInsert(Map<String, Object> map) {
		return sqlSession.insert("mapper.insert", map);
	}

	public Map<String, Object> boardDetail(int num) {
		return sqlSession.selectOne("mapper.detail", num);
	}




	

}
