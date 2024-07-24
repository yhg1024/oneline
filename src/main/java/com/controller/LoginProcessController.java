package com.controller;

import java.io.IOException;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.dto.MemberDto;

@WebServlet("/loginProcess")
public class LoginProcessController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 1. ��û �Ķ���� ����
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// 2. MemberDao�� �̿��Ͽ� ����� ����
		//		id, pass ��ġ�ϴ� ����� �ִٸ� member��ü ��ȯ �ƴϸ� null ��ȯ
		MemberDao dao = new MemberDao();
		MemberDto dto = dao.login(id, pass);
		
		if(dto != null) {
			// �α��� ����
			// 3. session ������ memberDto ����
			HttpSession session = request.getSession();
			// ������ ����Ⱓ�� �����ְ� ���������� ���������� ���� ���� ����
			session.setAttribute("memberDto", dto);
			session.setAttribute("userId", id);
			
			// 4. ������ ��ȯ
			response.sendRedirect("main.jsp");
		} else  {
			// �α��� ����
			request.getRequestDispatcher("loginForm.jsp?isError=1").forward(request, response);
		}
		
	}
}
