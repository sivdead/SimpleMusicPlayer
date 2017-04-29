package cn.com.musicplayerserver.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.com.musicplayerserver.vo.AlbumTableVO;


public class AlbumTableModel extends AbstractTableModel{

	private String headers[] = {"ÐòºÅ","×¨¼­","¸èÊÖ"};
	private List<AlbumTableVO> albumList = null;
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (albumList == null)
			return 0;
		return albumList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return headers[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (albumList == null){
			return null;
		}
		Object obj = null;
		AlbumTableVO album = albumList.get(rowIndex);
		switch(columnIndex){
		case 0:obj = rowIndex+1;
			break;
		case 1:obj = album.getAlbumName();
			break;
		case 2:obj = album.getSinger();
			break;
		}
		return obj;
	}


	public List<AlbumTableVO> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<AlbumTableVO> albumList) {
		this.albumList = albumList;
		this.fireTableDataChanged();
	}

}
