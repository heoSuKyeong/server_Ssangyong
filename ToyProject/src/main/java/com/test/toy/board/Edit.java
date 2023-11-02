package com.test.toy.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/edit.do")
public class Edit extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Edit.java
		
		//1. 데이터 수신(seq)
		//2. DB작업(select) 위임
		//3. 피드백
		
		if (Auth.check(req, resp)) {
			//권한이 없으면 실행하지 않음
			return;
		}
		
		//1.
		String seq = req.getParameter("seq");
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = dao.get(seq);
		
		String subject = dto.getSubject();
		
		// 큰따옴표(") 를 \" 로 바꾸기
		subject = subject.replace("\"", "&quot;");
		dto.setSubject(subject);
		
		//3.
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
		dispatcher.forward(req, resp);

	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//EditOk.java
		
		//1. 데이터 수신
		//2. DB 작업(update)
		//3. 피드백
		
		//session 객체 : 자주 사용할 예정이기에 따로 변수로 빼둔다.
		HttpSession session = req.getSession();
		
		//1.
		String seq = req.getParameter("seq");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		BoardDTO dto = new BoardDTO();
		dto.setSeq(seq);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setId(session.getAttribute("id").toString());
		
		int result = dao.edit(dto);
		
		//3.
		if (result == 1) {
			
			resp.sendRedirect("/toy/board/view.do?seq="+seq);
		}else {
			//0 또는 에러
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('Edit failed');history.back();</script>");
			writer.close();
		}
		
		
		
	}
}