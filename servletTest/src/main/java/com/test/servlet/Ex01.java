package com.test.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Ex01 extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
		
		resp.setCharacterEncoding("UTF-8");
	 	PrintWriter writer = resp.getWriter();
	 	
	 	writer.println("<html>");
	 	writer.println("<head>");
	 	writer.println("<meta charset='UTF-8'>");
	 	writer.println("<style>");
	 	writer.println("h1 {color: 'cornflowerblue';}");
	 	writer.println("</style>");
	 	writer.println("</head>");
	 	writer.println("<body>");
	 	writer.println("<h1>hello</h1>");
	 	writer.println("<p>Servlet으로 페이지를 만들었습니다.</p>");
	 	writer.println("</body>");
	 	writer.println("</html>");
	 	
	 	writer.close();   //일종의 스트림
	 	
	 	
	 	
	}
	
}
