package cn.com.musicplayerserver.service;

import java.net.UnknownHostException;
import java.util.List;

import javax.media.Time;

import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Music;
import cn.com.musicplayerserver.entity.PlayList;
import cn.com.musicplayerserver.entity.Singer;


public interface IMusicService {
	
	//1.搜歌(String keyword)
	//根据关键字搜索
	/**
	 * 
	 * @param keyword关键字
	 * @return歌曲对象
	 */
	public List<Music> searchMusic(String keyword);
	/**
	 * 
	 * @param keyword
	 * @return专辑
	 */
	public List<Album> searchAlbum(String keyword);
	/**
	 * 
	 * @param keyword
	 * @return歌手
	 */
	public List<Singer> searchSinger(String keyword);
	
	public void addToList(Music muisc,PlayList playList);
	/**
	 * 从歌单删除歌曲
	 * 
	 * @param musicName歌名
	 * @param playListName歌单名
	 * @return操作结果
	 */
	public Boolean deleteFromList(Music music,PlayList playList);
	//4.下载歌曲(歌名,歌手)
	/**
	 * 根据歌名下载歌曲
	 * 
	 * @param musicName歌名
	 * @return 返回操作结果
	 * @throws UnknownHostException  未知的主机地址
	 */
	public Boolean uploadMusic(Music music) throws UnknownHostException;
	/**
	 * 删除本地音乐
	 * 
	 * @param musicName歌名
	 * @return 操作结果
	 */
	public Boolean deleteMusic(Music music);
	
	public Boolean addAlbum(Album album);
	public Boolean updateAlbum(Album album);
	public Boolean delAlbum(Album album);
	public Boolean addSinger(Singer singer);
	public Boolean updateSinger(Singer singer);
	public Boolean delSinger(Singer singer);
	//public Boolean addPlayList(PlayList playList);
	//public Boolean updatePlayList(PlayList playList);
	//public Boolean delPlayList(PlayList playList);
}
