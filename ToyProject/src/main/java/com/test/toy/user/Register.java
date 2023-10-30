package com.test.toy.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.java.model.UserDTO;
import com.test.java.user.repository.UserDAO;

@WebServlet("/user/register.do")
public class Register extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Register.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/user/register.jsp");
		dispatcher.forward(req, resp);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//RegisterOk.java 역할
		//1. 데이터 수신
		//2. DB 작업(insert)
		//3. jsp 호출하여 피드백
		
		
		//"multipart/form-data"으로 인코딩을 넘겼으므로 req.getParameter() 동작이 불가능하다
		//MultipartRequest로 대체한다.
		
		try {
			MultipartRequest multi = new MultipartRequest(req,
															req.getRealPath("/asset/pic"),
															1024*1024*10,
															"UTF-8",
															new DefaultFileRenamePolicy()
															);
			//System.out.println(req.getRealPath("/asset/pic"));
			
			String id = multi.getParameter("id");
			String pw = multi.getParameter("pw");
			String name = multi.getParameter("name");
			String email = multi.getParameter("email");
			String pic = multi.getFilesystemName("pic");
			String intro = multi.getParameter("intro");
			
			System.out.println(intro);
			
			UserDTO dto = new UserDTO();
			dto.setId(id);
			dto.setPw(pw);
			dto.setName(name);
			dto.setEmail(email);
			dto.setPic(pic);
			dto.setIntro(intro);
			
			UserDAO dao = new UserDAO();
			
			int result = dao.register(dto);
			
			if (result == 1) {
				resp.sendRedirect("/toy/index.do");
			}
			
			
		} catch (Exception e) {
			System.out.println("Register.doPost()");
			e.printStackTrace();
		}
		
		//0 또는 에러
		PrintWriter writer = resp.getWriter();
		writer.print("<script>alert('failed');history.back();</script>");
		writer.close();
		
		
	}

}