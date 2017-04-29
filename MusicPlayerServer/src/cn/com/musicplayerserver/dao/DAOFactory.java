package cn.com.musicplayerserver.dao;

import cn.com.musicplayerserver.dao.impl.AlbumDAOImpl;
import cn.com.musicplayerserver.dao.impl.ManagerDAOImpl;
import cn.com.musicplayerserver.dao.impl.MusicDAOImpl;
import cn.com.musicplayerserver.dao.impl.SingerDAOImpl;
import cn.com.musicplayerserver.dao.impl.SystemParamDAOImpl;

public class DAOFactory {
	public static ISingerDAO createSingerDAO(){
		return new SingerDAOImpl();
	}
	public static IAlbumDAO createAlbumDAO(){
		return new AlbumDAOImpl();
	} 
	public static IManagerDAO createManagerDAO(){
		return new ManagerDAOImpl();
	}
	public static ISystemParamDAO createSystemParamDAO(){
		return new SystemParamDAOImpl();
	}
	public static IMusicDAO createMusicDAO() {
		// TODO 自动生成的方法存根
		return new MusicDAOImpl();
	}
}
