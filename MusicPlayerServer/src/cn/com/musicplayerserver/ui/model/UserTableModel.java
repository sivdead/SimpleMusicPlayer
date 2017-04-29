package cn.com.musicplayerserver.ui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.vo.AlbumTableVO;


public class UserTableModel extends AbstractTableModel{

	private String headers[] = {"序号","用户类型","用户名","邮箱"};
	private List<Manager> ManagerList = null;
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (ManagerList == null)
			return 0;
		return ManagerList.size();
	}

	public List<Manager> getManagerList() {
		return ManagerList;
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
		if (ManagerList == null){
			return null;
		}
		Object obj = null;
		Manager Manager = ManagerList.get(rowIndex);
		switch(columnIndex){
		case 0:obj = rowIndex+1;
			break;
		case 1:obj = Manager.getUserType();
			break;
		case 2:obj = Manager.getUserName();
			break;
		case 3:obj = Manager.getMail();
		    break;
		}
		return obj;
	}
	public Manager getManager(int rowIndex){
		Manager manager = null;
		if (ManagerList != null && rowIndex != -1){
			manager = ManagerList.get(rowIndex);
		}
		return manager;
	}


	public void setManagerList(List<Manager> ManagerList) {
		this.ManagerList = ManagerList;
		this.fireTableDataChanged();
	}

}
