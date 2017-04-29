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
		String msg = "用户"+user.getUserName()+":\n\r\t您的账号正在申请找回密码服务,验证码为:xxxxxx,如非本人操作,请无视这条邮件并检查账号是否被盗";
		code = Utils.getRandomCode();
		msg = msg.replace("xxxxxx", code);
		MailSendInfo mailInfo  = new MailSendInfo();
		mailInfo.setHost("smtp.163.com");
		mailInfo.setUserName("sivd_musicPlayer@163.com");
		mailInfo.setPassword("admin123");
		mailInfo.setSubject("找回密码");
		mailInfo.setContent(msg);
		mailInfo.setFromAddress("sivd_musicPlayer@163.com");
		mailInfo.setToAddress(user.getMail());
		MailSender sender = new MailSender();
		boolean bool  = sender.sentTextMail(mailInfo);
		return code;
	}

}
