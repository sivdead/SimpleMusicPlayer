package cn.com.musicplayerserver.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.ui.adddata.AddAdmin;
import cn.com.musicplayerserver.ui.model.UserTableModel;
public class UserPopupMenu extends JPopupMenu {
	private Manager manager = null;
	private JTable table = null;
	public UserPopupMenu(Manager manager,JTable table){
		String str[] = {"添加","删除","修改"};
		MyListener listener = new MyListener();
		for (String s:str){
			JMenuItem mnit= new JMenuItem(s);
			this.add(mnit);
			mnit.setName(s);
			mnit.addActionListener(listener);
		}
		this.manager = manager;
		this.table = table;
	}
	class MyListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JMenuItem mnit = (JMenuItem)e.getSource();
			switch(mnit.getName()){
			case "添加":
				if (MainUI.currUser != null && MainUI.currUser.getUserType() == 3){
					AddAdmin addAdmin = AddAdmin.getInstance();
					addAdmin.setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "错误：权限不足");
				break;
			case "删除":
				IManagerDAO userDAO = DAOFactory.createManagerDAO();
				userDAO.deleteUser(manager);
				UserTableModel userModel = (UserTableModel)table.getModel();
				List<Manager> userList = userModel.getManagerList();
				userList.remove(manager);
				userModel.setManagerList(userList);
				break;
//			case "修改":
//				addAdmin = AddAdmin.getInstance();
////				addAdmin
//				break;
			}
		}
		
	}
}
