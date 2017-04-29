package cn.com.musicplayerserver.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;

import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.mail.MailSendInfo;
import cn.com.musicplayerserver.mail.MailSender;
import cn.com.musicplayerserver.service.IMailService;
import cn.com.musicplayerserver.util.Utils;


public class MailServiceImpl implements IMailService {

	public String sendVerifyCode(Manager user) throws UnknownHostException {
		String code = null;
		String msg = "�û�"+user.getUserName()+":\n\r\t�����˺����������һ��������,��֤��Ϊ:xxxxxx,��Ǳ��˲���,�����������ʼ�������˺��Ƿ񱻵�";
		code = Utils.getRandomCode();
		msg = msg.replace("xxxxxx", code);
		MailSendInfo mailInfo  = new MailSendInfo();
		mailInfo.setHost("smtp.163.com");
		mailInfo.setUserName("sivd_musicPlayer@163.com");
		mailInfo.setPassword("admin123");
		mailInfo.setSubject("�һ�����");
		mailInfo.setContent(msg);
		mailInfo.setFromAddress("sivd_musicPlayer@163.com");
		mailInfo.setToAddress(user.getMail());
		MailSender sender = new MailSender();
		boolean bool  = sender.sentTextMail(mailInfo);
		return code;
	}

}
