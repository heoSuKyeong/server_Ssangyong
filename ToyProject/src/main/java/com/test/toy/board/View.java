package com.test.toy.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.toy.board.model.BoardDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/view.do")
public class View extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//View.java
		HttpSession session = req.getSession();
		
		//1.
		String seq = req.getParameter("seq");
		
		String search = req.getParameter("search");
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		//조회 수 증가
		//list를 통해 오지않고 즐겨찾기나 다른 경로로 view를 접근할 가능성이 있기에 read 세션은 null값일 수 있다.
		if (session.getAttribute("read") != null && session.getAttribute("read").toString().equals("n") ) {
			dao.updateReadcount(seq);
			session.setAttribute("read", "y");
		}
		
		BoardDTO dto = dao.get(seq);
		
		
		//2.5 데이터 가공
		String content = dto.getContent();
		//사용자가 태그를 입력했을 때 비활성화 처리
		//<> 태그를 &lt;div&gt; 로 바꿔주기
		
		//content = content.replace("<", "&lt;");
		//content = content.replace(">", "&gt");
		content = content.replace("<", "&lt;").replace(">", "&gt");
		
		String subject = dto.getSubject();
		subject = subject.replace("<", "&lt;").replace(">", "&gt");
		
		//개행 문자 처리
		content = content.replace("\r\n", "<br>");
				
		dto.setContent(content);
		dto.setSubject(subject);
		
		
		//내용으로 검색했을 때 검색어를 강조하는 구문
		if (search.equals("y") && column.equals("content")) {
			
			//이제 붕어빵은 서민 음식이 아닙니다.
			//이제 <span style="backgroud-color:gold;color:tomato;">붕어빵</span> 은 서민 음식이 아닙니다.	//인라인 스타일 적용
		 	dto.setContent(dto.getContent().replace(word, "<span style=\"background-color:gold;color:tomato;\">" + word + "</span>"));
		}
		
		
		//3. JSP 호출
		req.setAttribute("dto", dto);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);

	}

}