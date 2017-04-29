package cn.com.musicplayerserver.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.com.musicplayerserver.vo.MusicTableVO;


public class MusicTableModel  extends AbstractTableModel{
	private List<MusicTableVO> musicList = null;
	private String[] headers = {"序号","歌名","歌手","专辑","长度"};
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (musicList == null)
			return 0;
		else
			return musicList.size();
	}

	public String getColumnName(int column) {
		return headers[column];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (musicList == null){
			return null;
		}else{
			Object obj = null;
			MusicTableVO music = musicList.get(rowIndex);
			switch(columnIndex){
			case 0:
				obj = rowIndex+1;
				break;
			case 1:
				obj = music.getMusicName();
				break;
			case 2:
				obj = music.getSinger();
				break;
			case 3:
				obj = music.getAlbum();
				break;
			case 4:
				obj = music.getLength();
				break;
			}
			return obj;
		}
	}

	public List<MusicTableVO> getMusicList() {
		return musicList;
	}

	public void setMusicList(List<MusicTableVO> musicList) {
		this.musicList = musicList;
		this.fireTableDataChanged();
	}

	public MusicTableVO getMusic(int rowIndex){
		MusicTableVO music = null;
		if (musicList != null){
			music = musicList.get(rowIndex);
		}
		return music;
	}
}
