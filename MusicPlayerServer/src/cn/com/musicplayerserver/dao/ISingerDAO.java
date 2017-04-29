package cn.com.musicplayerserver.dao;

import java.util.List;

import cn.com.musicplayerserver.entity.Singer;

public interface ISingerDAO {
    public boolean addSinger(Singer singer);
    public boolean updateSinger(Singer singer);
    public boolean delSinger(Singer singer);
    public List<Singer> selectSinger(String singer);
    public Singer selectSinger(int albumId);
	public Singer getSingerBySingerId(Integer singerId);
	public Singer getSingerByAlbum(String keyword);
	public List<Singer> getSingerByName(String keyword);
	public Singer getSingerByMusic(String keyword);
}
