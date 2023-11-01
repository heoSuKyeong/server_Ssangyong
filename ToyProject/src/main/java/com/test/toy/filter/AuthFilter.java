package com.test.toy.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		//권한 체크
		//모든 권한 체크를 할 수 있는건 아니고 일부만 가능하다
		
		//System.out.println("권한 체크 필터");
		
		//인증 받지 못한 사용자 차단하기
		//HttpServletRequest 서블릿의 request와 다르다
		//그냥 ServletRequest 에는 getsession이 존재하지않아서 형변환하여 getsession을 사용한다.
		HttpServletRequest httpReq = (HttpServletRequest)req;
		HttpServletResponse httpResp = (HttpServletResponse)resp;

		HttpSession session =  httpReq.getSession();
		
		
		
		//확인용
		/*
		if (session.getAttribute("id") == null) {
			System.out.println("비회원");
		} else {
			System.out.println("id: " + session.getAttribute("id"));
		}
		System.out.println();
		*/
		
		
		//권한 미소유자 배제
		//어떤 페이지를 보려고 왔는지 확인
		//System.out.println(httpReq.getRequestURI());	//'/toy/board/list.do'
		
		//비회원이 글쓰기를 눌렀을 때 로그인 화면으로 이동
		if (session.getAttribute("id") == null) {
			if (httpReq.getRequestURI().endsWith("add.do")
					|| httpReq.getRequestURI().endsWith("edit.do") 
					|| httpReq.getRequestURI().endsWith("del.do")
					|| httpReq.getRequestURI().endsWith("info.do")) {
				//바로 페이지 이동시키지않고 안내메시지로 안내해준다.
				//httpResp.sendRedirect("/toy/user/login.do");
				
				PrintWriter writer = httpResp.getWriter();
				writer.write("<script>alert('Login is required.'); location.href='/toy/user/login.do';</script>");
				writer.close();
				
				return;
			}
		}
		//응답이 이미 커밋된 후에는 forward할 수 없습니다.
		//아래의 doFilter가 실행되어 sendRedirect 가 이중으로 연달아 실행됐기 때문에 에러가난다.
		
		//서블릿 호출(필터 다음으로 서블릿으로 이어진다.)
		chain.doFilter(req, resp);
		
	}

}
