package cn.com.musicplayerserver.service;

import java.net.UnknownHostException;

import cn.com.musicplayerserver.entity.Manager;


public interface IMailService {
	/**
	 * 发送验证邮件
	 * 
	 * @param user
	 * @return String发送的验证码 
	 * @throws UnknownHostException 
	 */
	public String sendVerifyCode(Manager user) throws UnknownHostException;
}
