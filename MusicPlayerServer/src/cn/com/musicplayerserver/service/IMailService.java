package cn.com.musicplayerserver.service;

import java.net.UnknownHostException;

import cn.com.musicplayerserver.entity.Manager;


public interface IMailService {
	/**
	 * ������֤�ʼ�
	 * 
	 * @param user
	 * @return String���͵���֤�� 
	 * @throws UnknownHostException 
	 */
	public String sendVerifyCode(Manager user) throws UnknownHostException;
}
