package com.test.toy.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.toy.board.model.CommentDTO;
import com.test.toy.board.repository.BoardDAO;

@WebServlet("/board/comment.do")
public class Comment extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Comment.java

		//1. 데이터 수신(bseq)
		//2. DB 작업(select) 위임
		//3. 목록반환 받아 JSON으로 반환하기
		
		//1.
		String bseq = req.getParameter("bseq");
		
		//2.
		BoardDAO dao = new BoardDAO();
		ArrayList<CommentDTO> clist = dao.listComment(bseq);
		
		//3.
		JSONArray arr = new JSONArray();
		
		for (CommentDTO dto : clist) {
			//CommentDTO 1개를 JSONObject 1개와 맵핑
			JSONObject obj = new JSONObject();
			obj.put("seq", dto.getSeq());
			obj.put("content", dto.getContent());
			obj.put("id", dto.getId());
			obj.put("regdate", dto.getRegdate());
			obj.put("bseq", dto.getBseq());
			obj.put("name", dto.getName());
			
			arr.add(obj);
		}
		
		//4.
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.write(arr.toString());	//댓글 목록들
		writer.close();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 데이터 수신(content, bseq)
		//2. 인증 티켓을 확인하여 댓글 작성자 아이디확인
		//3. DB 작업(insert) 위임
		//4. ajax로 피드백
		
		//여러번 사용할 세션 미리 빼두기
		HttpSession session = req.getSession();
		
		//1.
		String content = req.getParameter("content");
		String bseq = req.getParameter("bseq");

		//2. 
		String id = session.getAttribute("id").toString();
		
		//3.
		BoardDAO dao = new BoardDAO();
		
		CommentDTO dto = new CommentDTO();
		dto.setContent(content);
		dto.setBseq(bseq);
		dto.setId(id);
		
		int result = dao.addComment(dto);
		
		//4.
		resp.setContentType("application/json");
		//결과에 한글이 포함되지않아 인코딩은 생략
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.write(obj.toString());
		writer.close();
		
	}

}