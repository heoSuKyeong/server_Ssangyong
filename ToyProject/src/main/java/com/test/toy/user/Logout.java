package com.test.toy.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/logout.do")
public class Logout extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Logout.java
		//인증티켓 제거
		req.getSession().removeAttribute("id");		
		req.getSession().removeAttribute("name");	
		req.getSession().removeAttribute("lv");	
		
		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('logout');</script>");
		
		resp.sendRedirect("/toy/index.do");
		writer.close();
	}

}