package com.com.com.service;

import java.util.List;
import com.com.com.domain.BoardVO;

public interface BoardService {
		
	// ����Ʈ ��ȸ
	public List<BoardVO> getAllBoards();

	// �Խù� ���
	public void insertBoard(BoardVO board) throws Exception;

	public BoardVO detail(int seq);


	// �Խù� ����

}
