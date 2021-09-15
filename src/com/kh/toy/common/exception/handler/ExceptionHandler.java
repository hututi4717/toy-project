package com.kh.toy.common.exception.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.toy.common.exception.HandlableException;


@WebServlet("/exception-handler")
public class ExceptionHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ExceptionHandler() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//서블릿 컨테이너는 HandlableException 이 발생하면 요청을 exception-handler로 재지정.
		// 이 때 request 의 javax.servlet.error.exception 속성에 발생한 예외를 함께 담아서 넘겨준다.
		HandlableException e = (HandlableException) request.getAttribute("javax.servlet.error.exception");
		
		request.setAttribute("msg", e.error.MESSAGE);
		request.setAttribute("url", e.error.URL);
		request.getRequestDispatcher("/WEB-INF/views/error/result.jsp").forward(request, response);
		// 요청이 재지정 되면서, wrapper에서 해놨던게 풀린듯.. 그래서 주소를 풀로 다 적어줘야한다.
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
