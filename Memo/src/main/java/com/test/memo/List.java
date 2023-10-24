package com.test.memo;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.memo.model.MemoDTO;
import com.test.memo.repository.MemoDAO;

@WebServlet("/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//List.java
		
		//1. DB에서 select 해오는 것을 DAO 위임
		//2. 결과테이블 반환받기
		//3. JSP 호출하여 결과테이블 전달
		
		//1 + 2
		MemoDAO dao = new MemoDAO();
		
		//ResultSet 는 DB작업의 객체이므로 resultset으로 반환받으면 안된다. > 
		ArrayList<MemoDTO> list = dao.list();
		
		//jsp에서는 뷰역할만 하기때문에 12자 넘어가는 글자를 자르게 보이는 작업은 서블릿에서 한다.
		for (MemoDTO dto : list) {
			if (dto.getMemo().length() > 12) {
				dto.setMemo(dto.getMemo().substring(0, 12) + "...");
			}
		}
		
		
		//3. 
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
		dispatcher.forward(req, resp);

	}

}