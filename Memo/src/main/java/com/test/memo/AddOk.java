package com.test.memo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.memo.model.MemoDTO;
import com.test.memo.repository.MemoDAO;

@WebServlet("/addok.do")
public class AddOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//AddOk.java
		//1. 데이터 수신(name, pw, memo)
		//2. DB를 통해 insert
		//3. JSP 호출하여 피드백
		
		//1. 
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String pw = req.getParameter("pw");
		String memo = req.getParameter("memo");
		
		//2. DB 작업은 위임받는 클래스를 따로 만들어 작업한다 > DB 담당자: MemoDAO.java
		//절대 서블릿에서 DB작업을 하지 않는다. 문제가 발생했을 때, DAO파일만 확인할 수 있게 담당을 확실하게 한다.
		//Connection conn = null;
		//PreparedStatement stat = null;
		
		// 하나의 계층이(Controller, Servlet) 또 다른 계층에게(MemoDAO) 데이터를 전송할 때 포장해서 넘긴다.
		// 포장하는 것은 컬렉션(HashMap) 또는 객체로 만든다는 것이다.
		MemoDAO dao = new MemoDAO();
		
		MemoDTO dto = new MemoDTO();
		dto.setName(name);
		dto.setPw(pw);
		dto.setMemo(memo);
		
		//dao.add(name, pw, memo);
		//전송하는 데이터가 2개 이상이면 DTO에 담아서 전송한다.
		int result = dao.add(dto);
		
		
		//3.
		req.setAttribute("result", result);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/addok.jsp");
		dispatcher.forward(req, resp);

	}

}
