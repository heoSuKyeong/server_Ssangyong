package com.test.toy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter{
	
	private String encoding;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//System.out.println("필터 생성");
		
		//web.xml의 'filter-name'를 읽어서 'param-value'인 UTF-8를 가져온다.
		encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		//System.out.println("필터 동작");
		
		//인코딩 처리
		req.setCharacterEncoding("UTF-8");
		
		//필터를 호출하면 서블릿도 호출해준다. forward의 역할과 유사하다.
		chain.doFilter(req, resp);
	}
	
	@Override
	public void destroy() {
		//System.out.println("필터 소멸");
	}

}
