package com.test.ajax.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.test.ajax.model.AddressDTO;
import com.test.ajax.respository.AjaxDAO;

@WebServlet("/list.do")
public class List extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//List.java
		//1. DB 작업 (select) 위임
		//2. 반환받은 값을 json으로 변환 후 ajax에게 반환
		
		//1. 
		AjaxDAO dao = new AjaxDAO();
		
		ArrayList<AddressDTO> list = dao.listAddress();
		
		//2.
		/*
		[
		 {
			 "seq":"5",
			 "name": "수수깡",
			 "pw": "1111",
			 "memo": "나는 수수깡이다",
			 "regdata": "2023-10-27"
			 
		 }
		 ]
		*/
		/*
		//object의 성질로 실수할 가능성이 낮아진다.
		//Json 문자열로 바꿔준다.
		JSONObject obj = new JSONObject();
		
		obj.put("name", "홍길동");
		obj.put("age", "20");
		
		JSONObject obj2 = new JSONObject();
		
		obj2.put("name", "아무개");
		obj2.put("age", "21");
		
		JSONArray arr = new JSONArray();
		arr.add(obj);
		arr.add(obj2);
		System.out.println(obj.toString());	//{"name":"홍길동","age":"20"}
		System.out.println(arr.toString());	//{"name":"홍길동","age":"20"}
		*/
		
		//ArrayList<AddressDTO> list를 JSONArray로 변환한다.
		JSONArray arr = new JSONArray();
		
		for (AddressDTO dto : list) {
			
			//AddressDTO 1개를 JSONObject 1개로 맵핑
			JSONObject item = new JSONObject();
			
			item.put("seq", dto.getSeq());
			item.put("name", dto.getName());
			item.put("age", dto.getAge());
			item.put("gender", dto.getGender());
			item.put("address", dto.getAddress());
			item.put("regdate", dto.getRegdate());
			
			arr.add(item);
			
		}
		//System.out.println(arr.toString());
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		
		writer.print(arr.toString());
		
		writer.close();
	}

}