package com.kh.toy.common.code;


//enum (enumerated type) : 열거형. 기본적으론 enum도 클래스임.
// 서로 연관된 상수들의 집합.
// 서로 연관된 상수들을 하나의 묶음으로 다루기 위한 enum만의 문법적 형식과 메서드를 제공

public enum MemberGrade {
	//회원등급코드 ME00은 info 가 '일반' 이고 연장가능횟수가 1회.
	//enum 은 내부적으로 class이다.
	// ME00 ("일반", 1) ->  public static final MemberGrade ME00 = new MemberGrade("일반",1);    인 것이다. 편하게 쓰기 위해 신텍스 슈거를 좀 친 것임.
	ME00("일반", "user", 1),
	ME01("성실", "user", 2),
	ME02("우수", "user", 3),
	ME03("VIP", "user", 4),
	
	AD00("super", "admin", 9999),
	AD01("member", "admin", 9999),  //회원 관리자 등급코드
	AD02("board", "admin", 9999);   //게시판 관리자 등급코드
	
	public final String DESC;
	public final String ROLE;
	public final int EXTENDABLE_CNT;
	
	
	//생성자가 프라이빗임. 즉, 외부에서 인스턴스 화 할 수 없음. 그러므로 안에서 인스턴스화 해야함.(그래서 위에 코드를 적은것)
	private MemberGrade(String desc, String role,  int extendableCnt) {
		this.DESC = desc;
		this.ROLE = role;
		this.EXTENDABLE_CNT = extendableCnt;
	}

	
}


//데이터베이스에서 코드관리를 한다면.. 데이터베이스에서 alter를 때려줘야하는것도잇고,, 자바에서 불러오는 코드들도 다 고쳐야 함..
//그래서 우리는 Enum 을 쓰기로 한 것..