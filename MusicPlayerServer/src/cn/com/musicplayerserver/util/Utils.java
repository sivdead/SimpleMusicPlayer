package cn.com.musicplayerserver.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.media.Time;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IAlbumDAO;
import cn.com.musicplayerserver.dao.ISingerDAO;
import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Music;
import cn.com.musicplayerserver.entity.Singer;
import cn.com.musicplayerserver.vo.MusicTableVO;


public class Utils {
	public static String getTime(Time time){
		String timeStr = null;
		int second = (int) time.getSeconds();
		int minute = (int) second/60;
		second = (int) second%60;
		if (minute<10){
			timeStr = "0"+minute;
		}else{
			timeStr = String.valueOf(minute);
		}
		if (second <10){
			timeStr += ":0"+second;
		}else{
			timeStr +=":"+second;
		}
		return timeStr;
	}
	public static String getTime(int time){
		String timeStr = null;
		int second = (int) time;
		int minute = (int) second/60;
		second = (int) second%60;
		if (minute<10){
			timeStr = "0"+minute;
		}else{
			timeStr = String.valueOf(minute);
		}
		if (second <10){
			timeStr += ":0"+second;
		}else{
			timeStr +=":"+second;
		}
		return timeStr;
	}
	public static List<MusicTableVO> transferToMusic(List<Music> musicList){
		List<MusicTableVO> musicVOList = null;
		ISingerDAO singerDAO = DAOFactory.createSingerDAO();
		IAlbumDAO albumDAO = DAOFactory.createAlbumDAO();
		if (musicList != null){
			Music music;
			musicVOList = new ArrayList<MusicTableVO>();
			MusicTableVO musicVO;
			for (int i = 0 ;i<musicList.size();i++){
				music = musicList.get(i);
				musicVO = new MusicTableVO();
				musicVO.setMusicName(music.getMusicName());
				if (music.getSingerId() != null){
					try{
						Singer singer = singerDAO.getSingerBySingerId(music.getSingerId());
						musicVO.setSinger(singer.getSingerName());
					}catch(Exception e){	
						System.err.println("ÍøÂçÁ¬½ÓÊ§°Ü");
					}
				}
				if (music.getAlbumId() != null){
					try{
						Album album = albumDAO.getAlbumByAlbumId(music.getAlbumId());
						musicVO.setAlbum(album.getAlbumName());
					}catch(Exception e){
						System.err.println("ÍøÂçÁ¬½ÓÊ§°Ü");
					}
				}
				musicVO.setLength(Utils.getTime(music.getMusicLength()));
				musicVOList.add(musicVO);
			}
		}
		return musicVOList;
	}
	public static String getRandomCode(){
		String code = "";
		Random rnd = new Random();
		for (int i = 0; i< 6 ; i++){
			code += rnd.nextInt(10);
		}
		return code;
	}
	public static List<Music> delDupMusic(List<Music> musicList) {
		List<Music> newList = new ArrayList<Music>();
		for (int i = 0;i<musicList.size();i++){
			if (!newList.contains(musicList.get(i))){
				newList.add(musicList.get(i));
			}
		}
		return newList;
	}
	public static List<Album> delDupAlbum(List<Album> albumList) {
		List<Album> newList = new ArrayList<Album>();
		for (int i = 0;i<albumList.size();i++){
			if (!newList.contains(albumList.get(i))){
				newList.add(albumList.get(i));
			}
		}
		return newList;
	}
	public static List<Singer> delDupSinger(List<Singer> singerList) {
		List<Singer> newList = new ArrayList<Singer>();
		for (int i = 0;i<singerList.size();i++){
			if (!newList.contains(singerList.get(i))){
				newList.add(singerList.get(i));
			}
		}
		return newList;
	}
}
