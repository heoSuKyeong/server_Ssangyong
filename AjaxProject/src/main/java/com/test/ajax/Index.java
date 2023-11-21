package com.test.ajax;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.DeptDTO;
import com.test.ajax.repository.DeptDAO;

@WebServlet("/index.do")
public class Index extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Index.java
		
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptDTO> list = dao.list();
		
		
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/index.jsp");
		dispatcher.forward(req, resp);

	}

}