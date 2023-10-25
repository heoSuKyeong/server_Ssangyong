package com.test.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.respository.AjaxDAO;

@WebServlet("/ex03data.do")
public class Ex03Data extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Ex03Data.java
		
		String name = req.getParameter("name");
		
		AjaxDAO dao = new AjaxDAO();
		
		int count = dao.getMemoCount(name);
		
		//int count = dao.getMemoCount();
		
		try {
			// 메모 갯수가 많아서 시간이 걸리게 설정한다.
			Thread.sleep(10000);
			
		} catch (Exception e) {
			System.out.println("Ex03Data.doGet()");
			e.printStackTrace();
		}
		
		
		
		PrintWriter writer = resp.getWriter();
		writer.print(count);
		writer.close();

		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex03data.jsp");
		//dispatcher.forward(req, resp);

	}

}