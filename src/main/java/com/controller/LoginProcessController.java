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
		// 1. 요청 파라미터 수집
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		// 2. MemberDao를 이용하여 사용자 인증
		//		id, pass 일치하는 사용자 있다면 member객체 반환 아니면 null 반환
		MemberDao dao = new MemberDao();
		MemberDto dto = dao.login(id, pass);
		
		if(dto != null) {
			// 로그인 성공
			// 3. session 영역에 memberDto 저장
			HttpSession session = request.getSession();
			// 세션의 만료기간이 남아있고 웹브라우저를 닫을때까지 세션 정보 유지
			session.setAttribute("memberDto", dto);
			session.setAttribute("userId", id);
			
			// 4. 페이지 전환
			response.sendRedirect("main.jsp");
		} else  {
			// 로그인 실패
			request.getRequestDispatcher("loginForm.jsp?isError=1").forward(request, response);
		}
		
	}
}
