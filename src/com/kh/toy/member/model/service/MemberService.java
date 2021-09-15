package com.kh.toy.member.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;

import com.kh.toy.common.db.JDBCTemplate;
import com.kh.toy.common.http.HttpConnector;
import com.kh.toy.common.http.RequestParams;
import com.kh.toy.common.mail.MailSender;
import com.kh.toy.member.model.dao.MemberDao;
import com.kh.toy.member.model.dto.Member;



//Service
//어플리케이션의 비즈니스 로직을 작성
//사용자의 요청을 컨트롤러로부터 위임받아 해당 요청을 처리하기 위해 필요한 핵심적인 작업을 진행.
//작업을 수행하기 위해 데이터베이스에 저장된 데이터가 필요하면 Dao에게 요청
//비즈니스 로직을 Service 가 담당하기 때문에 Transaction관리를 Service가 담당.

//	즉 commit, rollback 을 Service 가 담당. 
//Connection 객체 생성, close처리 commit, rollback, SQLException 에 대한 예외처리(Rollback) 까지를  Service 단에서 처리하게끔 해야 함.
//이에 맞게끔 전부 뜯어고쳐주자.

public class MemberService {
	
	private MemberDao memberDao = new MemberDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public Member memberAuthenticate(String userId, String password) {
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.memberAuthenticate(userId,password,conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}



	public Member selectMemberById(String userId) {
		
		Connection conn = template.getConnection();
		Member member = null;
		
		try {
			member = memberDao.selectMemberById(userId, conn);
		} finally {
			template.close(conn);
		}
		
		return member;
	}



	public List<Member> selectMemberList() {
		Connection conn = template.getConnection();
		List<Member> memberList = null;
		
		try {
			memberList = memberDao.selectMemberList(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(conn);
		}
		
		return memberList;
	}


	
	

	public int insertMember(Member member) {
		Connection conn = template.getConnection();
		//회원가입을 진행하고
		int res = 0;
		try {
			res = memberDao.insertMember(member,conn);
			template.commit(conn);
			
		} catch (Exception e) {          // 다른 여러 서비스(파일아웃풋이라든지) 이 붙을 수 도 있으니 가장 상위의 Exception 을 써야지 데이터베이스와 java쪽 예외들을 여러가지로 
			template.rollback(conn);	// 잡아 줄 수 있다. 만일 특정한익셉션만 꼭 써야 한다면? 이 익셉션 위로 catch 블록을 추가해주자.
			throw e;   // 예외를 한번 더 컨트롤러로 던짐.. 컨트롤러에서 더 정확한 예외처리를 진행하기 위해서..
		} finally {
			template.close(conn);
		}
		//회원가입에 성공하면 아이디로 회원의 정보를 받아와서 Controller반환해주는 기능을 추가한다면?
		//성공 : 
		//Member member = memberDao.selectMemberById(member.getUserId(), conn);  이 코드가 추가될텐데,
		//Controller는 view회원정보를 반환해주고 view에서는 회원정보를 출력
		//위 처럼 가입한 정보를 다시 출력해주는 기능까지 추가하면, 데이터베이스에 2번 접근함. 즉 둘중 하나가 성공하고 하나가 실패하면? 트랜잭션상으론 실패임
		// 이런 경우 때문에 트랜잭션 관리를 서비스 단에서 해줘야 한다는 것.
		return res;
	}



	public int changePassword(String userId, String password, String newpassword) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = memberDao.changePassword(userId, password, newpassword, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e; // 예외를 한번 더 컨트롤러로 던짐.. 컨트롤러에서 더 정확한 예외처리를 진행하기 위해서..
		} finally {
			template.close(conn);
		}
		
		return res;
		
	}



	public int deleteAccount(String userId, String password) {
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = memberDao.deleteAccount(userId, password, conn);
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e; // 예외를 한번 더 컨트롤러로 던짐.. 컨트롤러에서 더 정확한 예외처리를 진행하기 위해서..
		} finally {
			template.close(conn);
		}
		
		return res;
		
	}



	public void authenticateByEmail(Member member, String persistToken) {
		MailSender mailSender = new MailSender();
		HttpConnector conn = new HttpConnector();
		
		String queryString = conn.urlEncodedForm(RequestParams
												.builder()
												.param("mailTemplate", "join-auth-mail")
												.param("userId", member.getUserId())
												.param("persistToken", persistToken).build());
				
		String response = conn.get("http://localhost:9090/mail?"+queryString);
		mailSender.sendEmail(member.getEmail(), "회원가입 축하합니다.", response);
		
	}

	
	
	
	

}



