package com.test.memo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.memo.model.MemoDTO;
import com.test.memo.repository.MemoDAO;

@WebServlet("/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//View.java
		
		//1. 데이터 수신(seq)
		//2. DB에서 select 해오는 것을 DAO 위임
		//3. 결과테이블 반환받기
		//4. JSP 호출하여 결과테이블 전달
		
		//1.
		String seq = req.getParameter("seq");
		
		//2.
		MemoDAO dao = new MemoDAO();
		
		//하나의 데이터를 넘기기때문에 DTO에 담지않고 전송한다.
		MemoDTO dto = dao.get(seq);
		
		//메모의 엔터처리
		dto.setMemo(dto.getMemo().replace("\r\n", "<br>"));
		
		
		//3. 
		req.setAttribute("dto", dto);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/view.jsp");
		dispatcher.forward(req, resp);

	}

}