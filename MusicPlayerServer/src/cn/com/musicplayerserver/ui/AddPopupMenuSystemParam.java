package cn.com.musicplayerserver.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.SystemParam;
import cn.com.musicplayerserver.ui.AddPopupMenuAlbum.MyListener;

public class AddPopupMenuSystemParam extends JPopupMenu {
	private JTextField textField = null;
	public AddPopupMenuSystemParam(List<SystemParam> SystemList,JTextField textField){
		MyListener listener = new MyListener();
		for (SystemParam s:SystemList){
			JMenuItem mnit = new JMenuItem(s.getName());
			this.add(mnit);
			mnit.setActionCommand(String.valueOf(s.getId()));
			mnit.addActionListener(listener);
		}
		this.textField = textField;
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem mnit = (JMenuItem)e.getSource();
			int singerId = Integer.parseInt(mnit.getActionCommand());
			textField.setText(singerId+"."+mnit.getText());
		}
		
	}
}
