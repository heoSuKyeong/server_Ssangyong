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

@WebServlet("/ex06data.do")
public class Ex06Data extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Ex06_checkid_Data.java
		//1. 데이터 가져오기(id)
		//2. DB 작업하여 id 중복 체크하기
		//3. 결과 반환
		
		//1.
		String id = req.getParameter("id");
		
		//2.
		AjaxDAO dao = new AjaxDAO();
		
		int result = dao.check(id); //가능(0), 불가능(1)
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.printf("{\"result\": %d}", result);
		
		
		writer.close();
				

	}

}