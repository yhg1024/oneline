package com.com.com.mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.com.com.domain.BoardVO;

@Mapper
public interface BoardMapper {

	public List<BoardVO> viewAll();

	public void boardWrite(BoardVO board);

	public BoardVO detail(int seq);
	
	public Map<String, Object> update(BoardVO vo);
}