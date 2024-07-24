package com.dao;

import java.sql.SQLException;

import com.common.DBConnPool;
import com.dto.MemberDto;

public class MemberDao extends DBConnPool{
	public MemberDto login(String id, String pass) {
		MemberDto memberDto = new MemberDto();
		String sql = "SELECT * "
						+ "FROM MEMBER\r\n"
						+ "		WHERE id=? "
						+ "AND pass=?";
		
		// �Է¹��� ����� ������ DB�κ��� ��ȸ
		try {
			pstmt = con.prepareStatement(sql);
			//�Ķ���� ����
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			
			//���� ����
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// �α��� ����
				memberDto.setID(rs.getString(rs.getNString(1)));
				memberDto.setNAME(rs.getString(2));
			
				// ����� ������ Dto�� ��Ƽ� ��ȯ
				return memberDto;
			} else {
				// �α��� ����
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
