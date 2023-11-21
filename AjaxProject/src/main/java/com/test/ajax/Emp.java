package com.test.ajax;

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

import com.test.ajax.model.EmpDTO;
import com.test.ajax.repository.EmpDAO;

@WebServlet("/emp.do")
public class Emp extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Emp.java
		
		String department_id = req.getParameter("department_id");
		
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpDTO> list = dao.list(department_id);
		
		JSONArray arr = new JSONArray();
		
		for (EmpDTO dto : list) {
			
			JSONObject obj = new JSONObject();
			
			obj.put("id", dto.getEmployee_id());
			obj.put("name", dto.getName());
			obj.put("job", dto.getJob_id());
			obj.put("date", dto.getHire_date());
			
			arr.add(obj);
			
		}
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter writer = resp.getWriter();
		writer.write(arr.toString());
		writer.close();
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/emp.jsp");
		dispatcher.forward(req, resp);

	}

}