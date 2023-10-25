package com.test.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.ajax.model.MemoDTO;
import com.test.ajax.respository.AjaxDAO;

@WebServlet("/ex04data.do")
public class Ex04Data extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Ex04Data.java
		
		//수신 : ex04data.do?type=1

		String type = req.getParameter("type");
		//System.out.println(type);
		if (type.equals("1")) {
			m1(req, resp);
		} else if (type.equals("2")) {
			m2(req, resp);
		} else if (type.equals("3")) {
			m3(req, resp);
		}
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/ex04data.jsp");
		//dispatcher.forward(req, resp);

	}
	private void m3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		AjaxDAO dao = new AjaxDAO();
		
		//메모 27번 
		MemoDTO dto = dao.get(27);
		
		//MemoDTO를 xml 형식을 변환하여 ajax에게 반환한다.
		resp.setCharacterEncoding("UTF-8");
		
		//setContentType : 절대적인 표현
		resp.setContentType("text/plain");
		//resp.setContentType("application/zip");
		
		PrintWriter writer = resp.getWriter();
		
		//xml 태그 작성시 띄어쓰기 주의!
		writer.print("<?xml version='1.0' encoding='UTF-8'?>");
		writer.print("<memo>");
		writer.printf("<seq>%s</seq>", dto.getSeq());
		writer.printf("<name>%s</name>", dto.getName());
		writer.printf("<pw>%s</pw>", dto.getPw());
		writer.printf("<memo>%s</memo>", dto.getMemo());
		writer.printf("<regdate>%s</regdate>", dto.getRegdate());
		writer.print("</memo>");
		
		writer.close();
	}

	private void m2(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//테이블 전체를 리턴
		
		AjaxDAO dao = new AjaxDAO();
		
		ArrayList<MemoDTO> list = dao.listMemo();
		
		//하나의 csv 형태의 데이터
		String temp="";
		
		for (MemoDTO dto : list) {
			temp += String.format("%s, %s, %s, %s, %s\r\n"
										, dto.getSeq()
										, dto.getName()
										, dto.getPw()
										, dto.getMemo().replace("\r\n", "\n")
										, dto.getRegdate());

		}
		System.out.println(temp);
		
		resp.setContentType("text/plain");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.print(temp);
		writer.close();
		
		
	}

	private void m1(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//단일 텍스트 반환
		AjaxDAO dao = new AjaxDAO();
		int count = dao.getMemoCount();
		
		//MIME 브라우저 또는 ajax 객체에게 돌려받는 데이터가 이런 형식의 데이터다 라고 알려주는 표시
		resp.setContentType("text/plain");	// text/plain : 순수 텍스트 데이터
		resp.setCharacterEncoding("UTF-8");
		
		//resp 예외처리 필수
		PrintWriter writer = resp.getWriter();
		writer.print(count);
		writer.close();
		
		
	}
}
