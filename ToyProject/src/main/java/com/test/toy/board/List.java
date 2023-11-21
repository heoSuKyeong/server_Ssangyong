package com.test.toy.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		//List.java 가 호출되는 3가지 주소
		//1. list.do : 목록 보기 + 1페이지
		//2. list.do?column=suject&word=검색어 : 검색결과
		//3. list.do?page=1 : 페이징
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String search = "n";		//검색이면 y, 목록보기는 n
		
		if ((column == null && word == null) || (column.equals("") && word.equals(""))) {
			search = "n";
		} else {
			search = "y";
		}
		
		//여기서만 사용하는 데이터들은 DTO를 만들지않고 HashMap으로 전송한다.
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("word", word);
		map.put("search", search);
		
		
		//페이징
		int nowPage = 0;		//현재 페이지 번호
		int totalCount = 0;		//총 게시물 수
		int pageSize = 10;		//한 페이지에서 출력할 게시물 수
		int totalPage = 0;		//총 페이지 수
		int begin = 0;			//페이징 시작 위치
		int end = 0;			//페이지 끝 위치
		
		int blockSize = 10;
		
		String page = req.getParameter("page");
		
		if (page == null || page.equals("")) {
			//기본 페이지는 1페이지로 설정
			nowPage = 1;
		} else {
			nowPage = Integer.parseInt(page);
		}
		
		//list.do?page=1 로 요청하면 DB에선 where rnum between 1 and 10 으로 처리해야한다.
		begin = ((nowPage -1) * pageSize) +1;
		end = begin + pageSize -1;
		
		map.put("begin", begin+"");
		map.put("end",  end+"");
		
		
		//1. DB 작업(select)
		//2. 결과테이블을 JSP로 출력하기  
		
		HttpSession session = req.getSession();
		
		//조회수를 한번만 증가하기 위해 조회 여부를 담는 티켓
		session.setAttribute("read", "n");
		
		//1. 
		BoardDAO dao = new BoardDAO();
		
		//계층간 데이터를 주고 받을 때 
		ArrayList<BoardDTO> list = dao.list(map);
		
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
		
		
		//총 게시물 수
		totalCount = dao.getTotalCount();
		totalPage = (int)Math.ceil((double)totalCount / pageSize);
		
		//페이지 바 계산하기
		StringBuilder sb = new StringBuilder();
		
		//전체 페이지 수를 나열하는 방식
		/*
		for (int i=1; i<=totalPage; i++) {
			if (i == nowPage) {
				//다시 자기를 눌렀을 때, 아무 반응이 없도록
				sb.append(String.format(" <a href='#!' style='color:tomato; font-weight: bold;'>%d</a> ", i));
			} else {
				sb.append(String.format(" <a href='/toy/board/list.do?page=%d'>%d</a> ", i, i));
			}
		}
		*/
		
		//이전 다음 버튼으로 페이지 수를 나열하는 방식
		int loop = 1;	//루프 변수(10바퀴)
		//int n = 1;		//출력 페이지 번호
		int n = ((nowPage - 1) / blockSize) * blockSize + 1;
		
		//[이전페이지]
		if (n ==1) {
			sb.append(" <a href='#!'>[이전페이지]</a>");
			//sb.append(String.format(" <a href='#!'>[다음페이지]</a>"));
		} else {
			sb.append(String.format(" <a href='/toy/board/list.do?page=%d'>[이전페이지]</a>", n-1));
		}
		
		while (!(loop>blockSize || n>totalPage)) {
			if (n == nowPage) {
				//다시 자기를 눌렀을 때, 아무 반응이 없도록
				sb.append(String.format(" <a href='#!' style='color:tomato; font-weight: bold;'>%d</a> ", n));
			} else {
				sb.append(String.format(" <a href='/toy/board/list.do?page=%d'>%d</a> ", n, n));
			}
			loop++;
			n++;
		}
		
		//[다음페이지]
		//위에서 n값이 blockSize보다 1 큰 값이 들아가있다.
		//마지막 페이지까지만 이동해야한다.
		if (n > totalPage) {
			sb.append(" <a href='#!'>[다음페이지]</a>");
			//sb.append(String.format(" <a href='#!'>[다음페이지]</a>"));
		} else {
			sb.append(String.format(" <a href='/toy/board/list.do?page=%d'>[다음페이지]</a>", n));
		}
		
		
		
		
		
		
		//2.
		req.setAttribute("list", list);
		req.setAttribute("map", map);	//검색내용 전달
		
		//페이징
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);

		req.setAttribute("pagebar", sb.toString());	//페이지 바
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/list.jsp");
		dispatcher.forward(req, resp);

	}

}