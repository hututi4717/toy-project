package com.kh.toy.member.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kh.toy.member.model.service.MemberService;

public class JoinForm {
	
	private String userId;
	private String password;
	private String email;
	private String tell;
	private HttpServletRequest request;
	private MemberService memberService = new MemberService();
	private Map<String,String> failedAttribute = new HashMap<String, String>();
	//통과 유무를 체크하기위한 맵. 맵으로 만든 이유는? jsp으로 꺼내 쓰기 편해서. 여기다 실패한 속성들을 담아 줄 것임.
	
	
	public JoinForm(HttpServletRequest request) {
		this.request = request;
		this.userId = request.getParameter("userId");
		this.password = request.getParameter("password");
		this.email = request.getParameter("email");
		this.tell = request.getParameter("tell");
	}
	
	public boolean test() {
		
		boolean res = true;
		boolean valid = true;
		
		//db에 존재하지 않는 아이디인지 확인
		if(memberService.selectMemberById(userId) != null) {
			failedAttribute.put("userId", userId);
			res = false;   //원래 있던 throw new exception 은, 필터에서 던지게끔 처리 할 것이다. 여기선 res=false로 해주자
		}
		
		//비밀번호가 영수특수문자 조합의 8자리 이상 문자열
		valid = Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", password);
		if(!valid) {
			failedAttribute.put("password", password);
			res = false;
		}
		
		//전화번호가 9~11 자리의 숫자
		valid = Pattern.matches("^\\d{9,11}$", tell);
		if(!valid) {
			failedAttribute.put("tell", tell);
			res = false;
		}
		
		if(!res) {
			request.getSession().setAttribute("joinFailed", failedAttribute);
			request.getSession().setAttribute("joinForm", this);
			//사용자가 입력한 parameter 값들이 이 joinForm 에 담겨있다. 그럼으로, this값도 담아주자.
		} else {
			request.getSession().removeAttribute("joinFailed");
			request.getSession().removeAttribute("joinForm");
		}

		return res;
				
	}
	
	

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getTell() {
		return tell;
	}

	
}
