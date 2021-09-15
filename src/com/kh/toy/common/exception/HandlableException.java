package com.kh.toy.common.exception;

import com.kh.toy.common.code.ErrorCode;

public class HandlableException extends RuntimeException {

	private static final long serialVersionUID = -3930409458039432875L;
	public ErrorCode error;
	
	public HandlableException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]);   //스택트레이스 날려서 로그에 안찍히게함
	}
	
	public HandlableException(ErrorCode error, Exception e) {
		this.error = error;
		e.printStackTrace();
		this.setStackTrace(new StackTraceElement[0]);
	}
	
	//1. 콘솔에 로그
	//2. (result.jsp로 했던거..)사용자에게 알림메시지 띄워주기, 경로 재지정
	// 		발생한 예외별 에러 메시지와, 재지정할 경로.
		
	
}
