package com.test.memo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//del.java
		
		//!!삭제 작업은 DelOk.java에서 진행한다. (DB작업은 Ok 파일에서 작성한다.)
		
		//1. 데이터 수신(seq)
		//2. JSP 호출하기
		
		//1. 
		String seq = req.getParameter("seq");
		
		//2.
		req.setAttribute("seq", seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/del.jsp");
		dispatcher.forward(req, resp);

	}

}
