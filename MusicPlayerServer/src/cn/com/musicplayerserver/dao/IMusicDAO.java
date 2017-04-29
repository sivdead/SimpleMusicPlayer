package cn.com.musicplayerserver.dao;

import java.util.List;

import cn.com.musicplayerserver.entity.Music;


public interface IMusicDAO {
	/**
	 * ���ݸ����ҵ�����
	 * 
	 * @param musicName
	 * @return Music���ֶ���,�Ҳ�������Null
	 */
	public List<Music> getMusicByName(String musicName);
	/**
	 * ���ݸ����Ҹ���
	 * 
	 * @param singer
	 * @return Music���ֶ���,�Ҳ�������Null
	 */
	public List<Music> getMusicBySinger(String singerName);
	/**
	 * ����ר���Ҹ���
	 * 
	 * @param album
	 * @return Music���ֶ���,�Ҳ�������Null
	 */
	public List<Music> getMusicByAlbum(String albumName);
	/**
	 * ���ݸ赥�Ҹ���
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

