package com.kh.toy.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EncodingFilter implements Filter {

    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
		//다음 filter에게 request 와  response를 전달
		//마지막 filter였다면 HttpServlet에게 request, response 전달
		//여기서 utf-8로 바꾸고 나서 , 다음 필터로 request, response를 넘김. 
		//만약 이게 마지막 필터라면? httpservlet 으로 넘김.
	}


	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
