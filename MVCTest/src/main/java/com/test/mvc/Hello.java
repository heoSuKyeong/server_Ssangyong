package com.test.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Hello extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//서블릿 호출
		//업무 처리 했다고 가정
		
		//HTML 페이지 생산한다.
		//하지만 서블릿에서 HTML 코드 작성하는 것이 불편하니 JSP에게 위임한다.
		//resp.sendRedirect("/mvc/hello.jsp");
	
		//DB 작업(select count(*))으로 받아온 결과물을 페이지끼리 공유를 한다.
		int count = 100;
		
		//서블릿이 자신의 업무를 완료한 후에 산출물 일부를 출력하기 위해 JSP 페이지에게 전달한다.
		req.setAttribute("count", count);
		
		//서블릿에는 pageContext 객체가 없어 사용하지 못한다.
		//pageContext.forward("/mvc/hello.jsp");
		
		//RequestDispatcher : pageContext의 기능을 한다.
		//무조건 '/'부터 시작하며 '/'는 webapp를 의미한다.
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/hello.jsp");
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/helo.jsp");
		dispatcher.forward(req, resp);	//pageContext.forward()와 유사한 기능을 가진다.
		
		
	}
	
}
