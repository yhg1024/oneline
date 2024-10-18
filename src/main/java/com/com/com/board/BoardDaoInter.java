package com.com.com.board;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

public interface BoardDaoInter {

	List<Map<String, Object>> list(Map<String, Object> map);

	int insert(Map<String, Object> map);

	Map<String, Object> detail(int seq);

	void delete(int seq);

	int update(Map<String, Object> map);

	void viewCnt(int seq);

	int totalCount(Map<String, Object> map);

	void file(Map<String, Object> map);

	List<Map<String, Object>> selectFileList(int seq);

	Map<String, Object> selectFileInfo(int seq);

	int insertFood(Map<String, Object> map);

	int updateFood(Map<String, Object> map);

	int deleteFood(Map<String, Object> map);

	List<Map<String, Object>> foodList();
}
