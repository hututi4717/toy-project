package com.kh.toy.common.exception;

import com.kh.toy.common.code.ErrorCode;

//예외처리가 강제되지 않는 UnCheckedException 상속
public class DataAccessException extends HandlableException {   //더이상 런타임 익셉션이 아니다.. 우리가 정한 공통모듈을 통해 다루기로 했으니 , 우리가 만든걸 상속시킨다.

	private static final long serialVersionUID = 521587827126031031L;
	
	public DataAccessException(Exception e) {   // 오류메세지를 받아서 생성한다.(super로 메세지를 보내면, runtimeException 이 해당메세지를 출력하는 듯 하다)
		super(ErrorCode.DATABASE_ACCESS_ERROR, e);
	}
	
}
