package com.test.toy.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//List.java
		
		//1. DB 작업(select)
		//2. 결과테이블을 JSP로 출력하기  
		
		HttpSession session = req.getSession();
		
		//조회수를 한번만 증가하기 위해 조회 여부를 담는 티켓
		session.setAttribute("read", "n");
		
		
		//1. 
		BoardDAO dao = new BoardDAO();
		
		//계층간 데이터를 주고 받을 때 
		ArrayList<BoardDTO> list = dao.list();
		
		//1.5 데이터 가공
		for (BoardDTO dto: list) {
			/*
			//시간 제외후 날짜만 출력 > sql에서 처리했으므로 주석처리
			String regdate = dto.getRegdate();
			dto.setRegdate(regdate.substring(0, 10));
		 */	
			
			//제목 길이 자르기
			String subject = dto.getSubject();
			
			if (subject.length() > 25) {
				subject = subject.substring(0, 25) + "...";
			}
			
			
			//사용자가 태그를 입력했을 때 비활성화 처리
			subject = subject.replace("<", "&lt;").replace(">", "&gt");
			
			dto.setSubject(subject);
		}
		
		
		//2.
		req.setAttribute("list", list);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);

	}

}