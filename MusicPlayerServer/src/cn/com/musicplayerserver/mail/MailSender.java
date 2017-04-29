package cn.com.musicplayerserver.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	/**
	 * 以文本格式发送邮�?
	 * @param mailInfo
	 * @return
	 */
	public boolean sentTextMail(MailSendInfo mailInfo){
		boolean bool = false;
		MyAuthenticator auth = null;
		Properties p = mailInfo.getProperties();
		if (mailInfo.isValidate()){
			auth = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
		}
		Session mailSession = Session.getDefaultInstance(p,auth);
		try{
			Message msg = new MimeMessage(mailSession);
			Address from = new InternetAddress(mailInfo.getFromAddress());
			msg.setFrom(from);
			Address to = new InternetAddress(mailInfo.getToAddress());
			msg.setRecipient(Message.RecipientType.TO,to);
			msg.setSubject(mailInfo.getSubject());
			msg.setSentDate(new Date());
			msg.setText(mailInfo.getContent());
			Transport.send(msg);
			bool = true;
		}catch(MessagingException e){
			e.printStackTrace();
		}
		return bool;
	}
	/**
	 * 以文本格式发送邮�?
	 * @param mailInfo
	 * @return
	 */
	public boolean sentHtmlMail(MailSendInfo mailInfo){
		boolean bool = false;
		MyAuthenticator auth = null;
		Properties p = mailInfo.getProperties();
		if (mailInfo.isValidate()){
			auth = new MyAuthenticator(mailInfo.getUserName(),mailInfo.getPassword());
		}
		Session mailSession = Session.getDefaultInstance(p,auth);
		mailSession.setDebug(true);
		try{
			Message msg = new MimeMessage(mailSession);
			Address from = new InternetAddress(mailInfo.getFromAddress());
			msg.setFrom(from);
			Address to = new InternetAddress(mailInfo.getToAddress());
			msg.setRecipient(Message.RecipientType.TO,to);
			msg.setSubject(mailInfo.getSubject());
			msg.setSentDate(new Date());
			MimeMultipart mainPart = new MimeMultipart();
			BodyPart html = new MimeBodyPart();
			html.setContent(mailInfo.getContent(),"text/html;charset=utf-8");
			mainPart.addBodyPart(html);
			msg.setContent(mainPart);
			Transport.send(msg);
			bool = true;
		}catch(MessagingException e){
			e.printStackTrace();
		}
		return bool;
	}
}
