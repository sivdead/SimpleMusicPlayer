package cn.com.musicplayerserver.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IAlbumDAO;
import cn.com.musicplayerserver.dao.IMusicDAO;
import cn.com.musicplayerserver.dao.ISingerDAO;
import cn.com.musicplayerserver.dao.impl.AlbumDAOImpl;
import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Music;
import cn.com.musicplayerserver.entity.PlayList;
import cn.com.musicplayerserver.entity.Singer;
import cn.com.musicplayerserver.service.IMusicService;
import cn.com.musicplayerserver.util.Tools;
import cn.com.musicplayerserver.util.Utils;

public class MusicServiceImpl implements IMusicService{

	// 1.�Ѹ�(String keyword)
	// ���ݹؼ�������
	/**
	 * 
	 * @param keyword�ؼ���
	 * @return��������
	 */
	@Override
	public List<Music> searchMusic(String keyword) {
		//�������и�Keyword�йص�����
		//1.���ݸ�������
		//2.����ר��.��������
		List<Music> musicList  = null;
		if (keyword != null && !keyword.trim().isEmpty()){
			musicList = new ArrayList<Music>();
			IMusicDAO musicDAO = DAOFactory.createMusicDAO();
			List<Music> tempList = new ArrayList<Music>();
			tempList = musicDAO.getMusicByAlbum(keyword);
			if (tempList != null)
				musicList.addAll(tempList);
			System.out.println(tempList.isEmpty());
			tempList = musicDAO.getMusicByName(keyword);
			if (tempList != null)
				musicList.addAll(tempList);
			System.out.println(tempList.isEmpty());
			tempList = musicDAO.getMusicBySinger(keyword);
			if (tempList != null)
				musicList.addAll(tempList);
			System.out.println(tempList.isEmpty());
			musicList = Utils.delDupMusic(musicList);
		}
		return musicList;
	}
	private List delDuplicate(List musicList) {
		List<Integer> delList = new ArrayList<Integer>(); 
		for (int i = 0;i<musicList.size()-1;i++){
			for(int j = i+1;j<musicList.size();j++){
				if (musicList.get(i) == musicList.get(j)){
					delList.add(j);
				}
			}
		}
		for (int i = 0 ; i < delList.size() ; i++){
			musicList.remove(delList.get(i));
		}
		return musicList;
	}


	/**
	 * 
	 * @param keyword
	 * @returnר��
	 */
	@Override
	public List<Album> searchAlbum(String keyword) {
		List<Album> list = null;
		System.out.println(keyword);
		if (keyword != null && !keyword.trim().isEmpty()){
			list = new ArrayList<Album>();
			IAlbumDAO albumDAO = DAOFactory.createAlbumDAO();
			List<Album >tempList = albumDAO.getAlbumByName(keyword);
			if (tempList != null)
				list.addAll(tempList);
			if (albumDAO.getAlbumByMusic(keyword) != null)
				list.addAll(albumDAO.getAlbumByMusic(keyword));
			if (albumDAO.getAlbumBySinger(keyword) != null)
				list.addAll(albumDAO.getAlbumBySinger(keyword));
			list = Utils.delDupAlbum(list);
		}
		return list;
	}

	/**
	 * 
	 * @param keyword
	 * @return����
	 */
	@Override
	public List<Singer> searchSinger(String keyword) {
		List<Singer> list = null;
		if (keyword != null && !keyword.trim().isEmpty()){
			list = new ArrayList<Singer>();
			ISingerDAO singerDAO = DAOFactory.createSingerDAO();
			List<Singer> tempList = singerDAO.getSingerByName(keyword);
			if (tempList != null)
				list.addAll(tempList);
			Singer singer = singerDAO.getSingerByAlbum(keyword);
			if (singer != null)
				list.add(singer);
			singer = singerDAO.getSingerByMusic(keyword);
			if (singer != null)
				list.add(singer);
			list = Utils.delDupSinger(list);
		}
		return list;
	}

	@Override
	public void addToList(Music muisc, PlayList playList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean deleteFromList(Music music, PlayList playList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean uploadMusic(Music music) throws UnknownHostException {
		IMusicDAO addMusicDAO = DAOFactory.createMusicDAO();
		boolean bool = false;
		bool = addMusicDAO.addMusic(music);
		return bool;
	}

	@Override
	public Boolean deleteMusic(Music music) {
		
		
		return null;
	}

	
	
	
	@Override
	public Boolean addAlbum(Album album) {
		IAlbumDAO addAlbumDAO = DAOFactory.createAlbumDAO();
		boolean bool = addAlbumDAO.addAlbum(album);
		return bool;
	}

	@Override
	public Boolean updateAlbum(Album album) {
		//����DAO
		IAlbumDAO delAlbumDAO = DAOFactory.createAlbumDAO();
		boolean bool = delAlbumDAO.updateAlbum(album);
		return bool;
	}

	@Override
	public Boolean delAlbum(Album album) {
		//����DAO
		IAlbumDAO delAlbumDAO = DAOFactory.createAlbumDAO();
		boolean bool = delAlbumDAO.delAlbum(album);
		return bool;
	}

	@Override
	public Boolean addSinger(Singer singer) {
		//����֤
		if(singer==null || Tools.isEmpty(singer.getSingerName())||Tools.isEmpty(singer.getBirthday())
				||Tools.isEmpty(singer.getNationId())){
			throw new RuntimeException("�������������ڣ���������Ϊ��");
		}
		boolean bool = false;
		// ��ȡVO���ݣ���װ��PO
		//�Ϸ��ĳ�������"^((((18|19)\\d{2}|20(0\\d|1[0-6]))-(((01|03|05|07|08|10|12)-(0[1-9]|(1|2)\\d|3(0|1)))|(02-(0[1-9]|1\\d|2[0-8]))|((04|06|09|11)-(0[1-9]|[1-2]\\d|30))))|(2017-(01-(0[1-9]|(1|2)\\d|3(0|1))|(02-0[1-8])))|((((18|19)((0|2|4|6|8)(0|4|8)|(1|3|5|7|9)(2|6)))|(20(00|04|08|12|16)))-02-29))$"
	    String birthRegex = "^((((18|19)\\d{2}|20(0\\d|1[0-6]))-(((01|03|05|07|08|10|12)-(0[1-9]|(1|2)\\d|3(0|1)))|(02-(0[1-9]|1\\d|2[0-8]))|((04|06|09|11)-(0[1-9]|[1-2]\\d|30))))|(2017-(01-(0[1-9]|(1|2)\\d|3(0|1))|(02-0[1-8])))|((((18|19)((0|2|4|6|8)(0|4|8)|(1|3|5|7|9)(2|6)))|(20(00|04|08|12|16)))-02-29))$";
		boolean bol = Tools.verifyRegex(singer.getBirthday().toString(), birthRegex);
	    if(!bol){
	    	JOptionPane.showMessageDialog(null, "���ֳ������ڸ�ʽ������");
	    	throw new RuntimeException("���ֳ������ڸ�ʽ������");
	    }else{
			// ����DAO
			ISingerDAO singerDAO = DAOFactory.createSingerDAO();
			// ��ȡ���ֶ���
			bool = singerDAO.addSinger(singer);
	    }
		return bool;
	}

	@Override
	public Boolean updateSinger(Singer singer) {
		//����DAO
		ISingerDAO singerDAO = DAOFactory.createSingerDAO();
		boolean bool = singerDAO.updateSinger(singer);
		return bool;
	}

	@Override
	public Boolean delSinger(Singer singer) {
		ISingerDAO singerDAO = DAOFactory.createSingerDAO();
		boolean bool = singerDAO.delSinger(singer);
		return bool;
	}

}
