package com.kh.toy.common.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import com.kh.toy.common.code.Config;
import com.kh.toy.common.code.ErrorCode;
import com.kh.toy.common.exception.HandlableException;

public class MailSender {

	private static final Properties SMTP_PROPERTIES;
	// 매번 생성할 코드가 아니기 때문에, 필드에 스태틱으로 두고, 초기화 블럭을 이용해 초기화 시켜주자.
	static {
		SMTP_PROPERTIES = new Properties();
		SMTP_PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
		SMTP_PROPERTIES.put("mail.smtp.port", "587");
		SMTP_PROPERTIES.put("mail.smtp.starttls.enable", "true");
		SMTP_PROPERTIES.put("mail.smtp.ssl.protocols", "TLSv1.2");
		SMTP_PROPERTIES.put("mail.smtp.auth", "true");
		SMTP_PROPERTIES.put("mail.debug", "true");
	}

	// 수신자, 메일 제목, 메일 내용(body) 는 외부에서 받아와야 함.
	// 수신자, 메일 제목, 메일 내용(body)
	public void sendEmail(String to, String subject, String body) {

		Session session = Session.getInstance(SMTP_PROPERTIES, null);

		try {
			MimeMessage msg = setMessage(session, to, subject, body);
			sendMessage(session, msg);
		} catch (MessagingException mex) {
			// 사용자에게 "메일 발송 중 문제가 생겼습니다" 안내 메세지 전달하고 인덱스 페이지로 리다이렉트
			// log에 에러의 stack-trace 기록
			throw new HandlableException(ErrorCode.MAIL_SEND_FAIL_ERROR, mex);
		}
	}

	// 메세지를 작성하는 코드와 , 메세지를 전송하는 코드를 분리하고, sendEmail 메서드는, 흐름만을 제어하게끔 분리시켜주자.
	private MimeMessage setMessage(Session session, String to, String subject, String body) throws MessagingException {
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(Config.COMPANY_EMAIL.DESC);
		msg.setRecipients(Message.RecipientType.TO, to);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		// text/html (앞에가 text로 잡히고, 서브타입으로 html이 잡히는 것)
		msg.setText(body, "UTF-8", "html");
		return msg;
	}

	private void sendMessage(Session session, MimeMessage msg) throws MessagingException {
		Transport tr = session.getTransport("smtp");
		tr.connect("smtp.gmail.com", Config.SMTP_AUTHENTICATION_ID.DESC, Config.SMTP_AUTHENTICATION_PASSWORD.DESC);
		msg.saveChanges();
		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}

}
