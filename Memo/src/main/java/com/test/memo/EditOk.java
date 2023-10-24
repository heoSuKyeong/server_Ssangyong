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

@WebServlet("/editok.do")
public class EditOk extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//EditOk.java
		
		//1. 데이터 수신(수정한 데이터)
		//2. DB에서 update 하는 것을 DAO 위임
		//3. 결과테이블 반환받아 피드백하는 것을 JSP로 전달
		
		//1.
		//수신된 데이터 인코딩
		req.setCharacterEncoding("UTF-8");
		
		String name = req.getParameter("name");
		String memo = req.getParameter("memo");
		String seq = req.getParameter("seq");
		String pw = req.getParameter("pw");		//글 수정, 삭제 권한 확인용
		
		//2.
		MemoDAO dao = new MemoDAO();
		
		MemoDTO dto = new MemoDTO();
		dto.setName(name);
		dto.setMemo(memo);
		dto.setSeq(seq);
		dto.setPw(pw);
		
		//글 번호에 대한 pw가 맞는지 검사
		boolean flag = dao.check(dto);
		
		int result = 0;//성공(1), 실패(0), 암호틀림(2)
		
		if (flag) {
			result = dao.edit(dto);
		} else {
			result = 2;
		}
		
		//3.
		req.setAttribute("result", result);
		req.setAttribute("seq", seq);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/editok.jsp");
		dispatcher.forward(req, resp);

	}

}