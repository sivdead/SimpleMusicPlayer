package cn.com.musicplayerserver.dao;

import java.util.List;

import cn.com.musicplayerserver.entity.Music;


public interface IMusicDAO {
	/**
	 * 根据歌名找到歌曲
	 * 
	 * @param musicName
	 * @return Music音乐对象,找不到返回Null
	 */
	public List<Music> getMusicByName(String musicName);
	/**
	 * 根据歌手找歌曲
	 * 
	 * @param singer
	 * @return Music音乐对象,找不到返回Null
	 */
	public List<Music> getMusicBySinger(String singerName);
	/**
	 * 根据专辑找歌曲
	 * 
	 * @param album
	 * @return Music音乐对象,找不到返回Null
	 */
	public List<Music> getMusicByAlbum(String albumName);
	/**
	 * 根据歌单找歌曲
	 * 
	 * @param playList
	 * @return
	 */
	public List<Music> getMusicByPlayList(String playListName);
	public Boolean addMusic(Music music);
	public Boolean updateMusic(Music music);
	public List<Music> getMusicRankByHeat();
	public List<Music> getMusicRankByDowload();
	public List<Music> getMusicRankBySearch();
	public Music getMusicById(int musicId );
}

