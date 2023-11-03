package com.test.toy.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/del.do")
public class Del extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Del.java
		
		if (Auth.check(req, resp)) {
			//권한이 없으면 실행하지 않음
			return;
		}
		
		//1. 데이터 수신(seq)
		//2. jsp 호출
		
		String seq = req.getParameter("seq");
		
		req.setAttribute("seq", seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/del.jsp");
		dispatcher.forward(req, resp);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//DelOk.java
		
		//1. 데이터 수신(seq)
		//2. DB작업(delete) 위임 + 게시물에 달린 댓글 삭제
		//3. 피드백
		
		//1.
		String seq = req.getParameter("seq");
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		//댓글 삭제
		dao.delCommentAll(seq);
		
		int result = dao.del(seq);

		// 3.
		if (result == 1) {
			resp.sendRedirect("/toy/board/list.do");
		} else {
			// 0 또는 에러
			PrintWriter writer = resp.getWriter();
			writer.print("<script>alert('Delete failed');history.back();</script>");
			writer.close();
		}
		
	}

}