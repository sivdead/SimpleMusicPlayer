package cn.com.musicplayerserver.dao;

import java.util.Collection;
import java.util.List;

import cn.com.musicplayerserver.entity.Album;

public interface IAlbumDAO {
	public boolean addAlbum(Album album); 
	public boolean delAlbum(Album album);
	public boolean updateAlbum(Album album);
	public List<Album> selectAlbum(String album);
	public List<Album> selectAlbum(String album,int singerid);
	public Album getAlbumByAlbumId(Integer albumId);
	public List<Album> getAlbumByMusic(String keyword);
	public List<Album> getAlbumBySinger(String keyword);
	public List<Album> getAlbumByName(String keyword);
}
