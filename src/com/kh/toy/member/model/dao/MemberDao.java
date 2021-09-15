package com.kh.toy.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.toy.common.db.JDBCTemplate;
import com.kh.toy.common.exception.DataAccessException;
import com.kh.toy.member.model.dto.Member;


//DAO(DATA ACCESS OBJECT)
//DBMS에 접근해 데이터의 조회, 수정, 삽입, 삭제 요청을 보내는 클래스
//DAO의 메서드는 하나의 메서드 당 하나의 쿼리만 처리하도록 작성. (그래야 하나의 메서드가 하나의 기능을 수행하게 됨. 역할분리가 이루어짐.)

public class MemberDao {
	
	
	//private JDBCTemplate template = new JDBCTemplate();       // jdbc템플릿이 여기에 있으면, 사람이 한명 들어올 때마다 새 인스턴스가 생성됨.
	//근데 jdbc템플릿이 사용자마다 다른 상태를 가질 수 있는 클래스인가? 아니다. 하나만들어쓰나 100개만들어쓰나 똑같다. 
	// 그래서 이 jdbc템플릿이 하나의 인스턴스만 가지게끔 처리를 해줄건데 이것이 SingleTon pattern 인다.
	// SingleTonpattern 에 관한 것은 JDBCTemplate 클래스에 적어놨으니 참조.
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	
	
	public Member memberAuthenticate(String userId, String password, Connection conn) {
		
		Member member = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member where user_id = ? and password = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				member = new Member();
				member.setUserId(rset.getString("user_id"));
				member.setPassword(rset.getString("password"));
				member.setEmail(rset.getString("email"));
				member.setGrade(rset.getString("grade"));
				member.setIsLeave(rset.getInt("is_leave"));
				member.setRegDate(rset.getDate("reg_date"));
				member.setRentableDate(rset.getDate("rentable_date"));
				member.setTell(rset.getString("tell"));
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);   //예외처리 메서드임으로 throw 할 수 있다.. 
		} finally {
			template.close(rset, pstm);

		}
		
		return member;
		
	}

	public Member selectMemberById(String userId, Connection conn)  {
		Member member = null;   //이부분은 리턴값이 member니까 바깥쪽으로 빼야 하는듯.
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "Select * from member where user_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			rset = pstm.executeQuery();
			

			if(rset.next()) {    //5단계
				member = convertAllToMember(rset);
				
			}

		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return member;
		
		
	}
	
	
	
	public List<Member> selectMemberList(Connection conn)  {
		
		List<Member> memberList = new ArrayList<Member>();
		//Member member = null;      //인스턴스 생성과정을 그냥 함수로 빼버렸으니, 이는 굳이 필요없는듯.
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from member";
		
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			
			while(rset.next()) {
				
				Member member = convertAllToMember(rset);
				memberList.add(member);
			}
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
			
		}
		
		
		return memberList;
	}

	//일단 지금은 오토커밋을 킨 채로 작업한다.. 오토커밋을 항상 켜두면 dcl이 의미가 없으니까... 그치만일단은 킨채로 작업 ㅇㅇ
	public int insertMember(Member member, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "insert into member(user_id, password, email, tell) values (?,?,?,?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, member.getUserId());
			pstm.setString(2, member.getPassword());
			pstm.setString(3, member.getEmail());
			pstm.setString(4, member.getTell());
			res = pstm.executeUpdate();    //dml 은 주로 삽입삭제등이므로 반환값이 리절트셋이 아니다. 그러므로 executeupdate를 쓴다.
			
			
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
			
		}
		
		
		
		return res;
		
	}
	
	
	
	public int changePassword(String userId, String password, String newpassword, Connection conn) {
		int res = 0;
		PreparedStatement pstr = null;
		String query = "update member set password = ? where user_id = ? and password = ?";	
		
		try {
			pstr = conn.prepareStatement(query);
			pstr.setString(1, newpassword);
			pstr.setString(2, userId);
			pstr.setString(3, password);
			
			res = pstr.executeUpdate();   // 이 순간에 위에 설정한 쿼리문이 집행되는듯.
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally { 
			template.close(pstr);
		}
		
		
		return res;
		
	}
	
	

	
	public int deleteAccount(String userId, String password, Connection conn) {
		int res = 0;
		PreparedStatement pstm = null;
		String query = "delete from member where user_id = ? and password = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, password);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}

		
		return res;
		
	}


	
	private Member convertAllToMember(ResultSet rset) throws SQLException{   //어차피 호출한 곳 밖에서 sql익셉션으로 잡아주고 있을테니 
		Member member = new Member();										//그냥 던져버리자.
		member.setUserId(rset.getString("user_id"));
		member.setPassword(rset.getString("password"));
		member.setEmail(rset.getString("email"));
		member.setGrade(rset.getString("grade"));
		member.setIsLeave(rset.getInt("is_leave"));
		member.setRegDate(rset.getDate("reg_date"));
		member.setRentableDate(rset.getDate("rentable_date"));
		member.setTell(rset.getString("tell"));
		return member;
	}
	
	
	//2021-07-15 수업 마지막교시 20분쯤부터 다시보기.  select * 로는 원하는 값만 가져올 수 없다. 그러므로, 잘라내서 보낼 수 있게끔 한 것.
	//  위에서(이건난안쳤음) 스픞릿으로 쿼리문을 잘라서 스트링배열로만들어서 전달하면, 그걸기반으로 dto에 값을 넣어버리는 것.
	private Member convertRowToMember(String[] columns, ResultSet rset) throws SQLException {
		Member member = new Member();
		for (int i = 0; i< columns.length; i++) {
			String column = columns[i].toLowerCase();
			column.trim();  //공백도 제거해버리기
			switch(column) {
			case "user_id" : member.setUserId(rset.getString("user_id")); break;
			case "password" : member.setPassword(rset.getString("password")); break;
			case "email" : member.setEmail(rset.getString("email")); break;
			case "grade" : member.setGrade(rset.getString("grade")); break;
			case "is_leave" : member.setIsLeave(rset.getInt("is_leave")); break;
			case "reg_date" : member.setRegDate(rset.getDate("reg_date")); break;
			case "rentable_date" : member.setRentableDate(rset.getDate("rentable_date")); break;
			case "tell" : member.setTell(rset.getString("tell")); break;
			default : throw new SQLException("부적적한 컬럼명을 전달했습니다.");  //예외처리..
			}
		}
		return member;
	}
	
	
	
	
	
	
	
}












































