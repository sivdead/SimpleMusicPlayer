package cn.com.musicplayerserver.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.com.musicplayerserver.vo.SingerTableVO;


public class SingerTableModel extends AbstractTableModel{
	private List<SingerTableVO> singerList = null;
	private String[] headers = {"序号","歌手","性别","国籍"};
	@Override
	public int getRowCount() {
		if (singerList == null){
			return 0;
		}else
			return singerList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return headers.length;
	}

	public List<SingerTableVO> getSingerList() {
		return singerList;
	}

	public void setSingerList(List<SingerTableVO> singerList) {
		this.singerList = singerList;
		this.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return headers[columnIndex];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (singerList == null){
			return null;
		}
		Object obj = null;
		SingerTableVO singer = singerList.get(rowIndex);
		switch(columnIndex){
		case 0:
			obj =  rowIndex+1;
			break;
		case 1:
			obj = singer.getSingerName();
			break;
		case 2:
			obj = singer.getSex();
			break;
		case 3:
			obj = singer.getNation();
			break;
		}
		return obj;
	}

	

}
