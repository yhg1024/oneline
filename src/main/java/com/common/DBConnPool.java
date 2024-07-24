package com.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnPool {
	public Connection con;
	public Statement stmt;
	public PreparedStatement pstmt;
	public ResultSet rs;
	
	public DBConnPool() {
			try {
				// ��Ĺ���� �о����
				// JNDI�� ���� Ŀ�ؼ� Ǯ ������
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
				
				// Ŀ�ؼ�Ǯ�� ���� ���� ��ü�� ������� con�� ����ݴϴ�.
				con = ds.getConnection();
				System.out.println("JNDI�� ���� Ŀ�ؼ�Ǯ���� Ŀ�ؼ��� ������");
				
			} catch (NamingException e) {
				System.out.println("============= DBConnPool NamingException");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("============= DBConnPool SQLException");
				e.printStackTrace();
			}

	}
	
	/**
	 *  �������� (�ڿ� �ݳ�)
	 */	
	public void close() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(pstmt != null) pstmt.close();
			if(con != null)	con.close();
			
			System.out.println("�ڿ� �ݳ� ����");
		} catch (Exception e) {
			System.out.println("�ڿ��ݳ��� ������ �߻� �Ͽ����ϴ�.");
			e.printStackTrace();
		}
	}
}