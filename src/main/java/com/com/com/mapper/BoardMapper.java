package com.com.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.com.com.domain.BoardVO;

@Mapper
public interface BoardMapper {

	// 리스트 조회
	public List<BoardVO> viewAll();

	public void boardWrite(BoardVO board);


}