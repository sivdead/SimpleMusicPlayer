package cn.com.musicplayerserver.service;

import cn.com.musicplayerserver.service.impl.AuthorityControlServiceImpl;
import cn.com.musicplayerserver.service.impl.AuthorityManageServiceImpl;
import cn.com.musicplayerserver.service.impl.MailServiceImpl;
import cn.com.musicplayerserver.service.impl.MusicServiceImpl;

public class ServiceFactory {
	public static IAuthorityControlService createAuthorityControlService(){
		return new AuthorityControlServiceImpl();
	}
	public static IAuthorityManageService createAuthorityManageService(){
		return new AuthorityManageServiceImpl();
	}
	public static IMusicService createMusicService(){
		return new MusicServiceImpl();
	}
	public static IMailService createMailService(){
		return new MailServiceImpl();
	}
}
