package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex02_Receive extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//데이터 수신
		//<input type="text" name="name">
		//<input type="text" name="age">
		
		//HttpServletRequest req
		//데이터를 수신하는 역할
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		
		System.out.println(name);
		System.out.println(age);
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		
		writer.println("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<h1>데이터 수신</h1>\r\n"
				+ "	<p>데이터 처리가 완료되었습니다.</p>\r\n"
				+ "	\r\n"
				+ "</body>\r\n"
				+ "</html>");
		writer.close();
		
	}
}
