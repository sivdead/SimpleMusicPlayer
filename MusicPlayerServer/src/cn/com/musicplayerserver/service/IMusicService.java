package cn.com.musicplayerserver.service;

import java.net.UnknownHostException;
import java.util.List;

import javax.media.Time;

import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Music;
import cn.com.musicplayerserver.entity.PlayList;
import cn.com.musicplayerserver.entity.Singer;


public interface IMusicService {
	
	//1.�Ѹ�(String keyword)
	//���ݹؼ�������
	/**
	 * 
	 * @param keyword�ؼ���
	 * @return��������
	 */
	public List<Music> searchMusic(String keyword);
	/**
	 * 
	 * @param keyword
	 * @returnר��
	 */
	public List<Album> searchAlbum(String keyword);
	/**
	 * 
	 * @param keyword
	 * @return����
	 */
	public List<Singer> searchSinger(String keyword);
	
	public void addToList(Music muisc,PlayList playList);
	/**
	 * �Ӹ赥ɾ������
	 * 
	 * @param musicName����
	 * @param playListName�赥��
	 * @return�������
	 */
	public Boolean deleteFromList(Music music,PlayList playList);
	//4.���ظ���(����,����)
	/**
	 * ���ݸ������ظ���
	 * 
	 * @param musicName����
	 * @return ���ز������
	 * @throws UnknownHostException  δ֪��������ַ
	 */
	public Boolean uploadMusic(Music music) throws UnknownHostException;
	/**
	 * ɾ����������
	 * 
	 * @param musicName����
	 * @return �������
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
