package com.com.com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.com.com.domain.BoardVO;

@Mapper
public interface BoardMapper {

	// ����Ʈ ��ȸ
	public List<BoardVO> viewAll();

	public void boardWrite(BoardVO board);


}