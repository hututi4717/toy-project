package com.kh.toy.member.model.dto;

import java.sql.Date;

//DTO (DATA TRANSFER OBJECT)
//데이터 전송객체
//데이터베이스로부터 얻어 온 데이터를 service(비즈니스로직)으로 보내기 위해 사용하는 객체
//*** 비즈니스 로직을 포함하고 있지 않은, 순수하게 데이터 전송만을 위해 사용되는 객체.
//getter/setter, equals, hashCode, toString 메서드 정도만을 가진다.

//***참고
// DTO랑 비슷한 친구들
// DOMAIN OBJECT, VALUE OBJECT(VO), ENTITY, BEAN 등이 있다.

//DTO의 조건(JAVA BEAN 규약) (원래는 규약이어서 안지키면 안됐었는데, spring 이 이 규약을 안지키면서..요즘은 필수는 아니다..)
//1. 모든 필드변수는 private 처리
//2. 모든 필드변수는 getter/setter 메서드를 가져야 한다.
//3. 반드시 기본 생성자가 존재할 것(매개변수가 있는 생성자가 있더라도, 기본생성자는 있어야 한다.)

//오라클- 자바 타입 매핑
//CHAR VARCHAR2 -> String
//DATE -> java.util.Date, java.sql.Date(우리가 쓰게 될 타입임)
//NUMBER -> int, double

public class Member {
	/*
	 * USER_ID VARCHAR2(36 CHAR)
	 * PASSWORD VARCHAR2(60 CHAR)
	 * EMAIL VARCHAR2(50 CHAR)
	 * GRADE CHAR(4 CHAR)
	 * TELL VARCHAR2(15 CHAR)
	 * REG_DATE DATE
	 * RENTABLE_DATE DATE
	 * IS_LEAVE NUMBER
	 */
	// sqld 에서 bm 의 member table 속덩들을 복사해왔다. 이를 기반으로 아래처럼 만들자.
	private String userId;
	private String password;
	private String grade;
	private String email;
	private String tell;
	private Date regDate;
	private Date rentableDate;
	private int isLeave;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getRentableDate() {
		return rentableDate;
	}
	public void setRentableDate(Date rentableDate) {
		this.rentableDate = rentableDate;
	}
	public int getIsLeave() {
		return isLeave;
	}
	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", grade=" + grade + ", email=" + email
				+ ", tell=" + tell + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", isLeave=" + isLeave
				+ "]";
	}
	
	
}
























