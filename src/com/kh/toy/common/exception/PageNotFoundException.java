package com.kh.toy.common.exception;

public class PageNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1313747291601761454L;
		
		public PageNotFoundException() {
			//stackTrace 를 비워줘서 콘솔창에 오류내역이 길게 안남게 한다.
			this.setStackTrace(new StackTraceElement[0]);
			//서블릿으로부터전달받은(우리가 예외처리로 설정해서 오류내역이 떴던(서블릿이 자동으로 띄워주는건 콘솔에 안찍히니까..)) 익셉션의 스택트레이스의 길이를 0으로 맞춘다.
		}
}
