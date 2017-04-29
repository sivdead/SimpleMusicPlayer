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
import cn.com.musicplayerserver.entity.Singer;

public class AddPopupMenuAlbum extends JPopupMenu{
	private JTextField txtAlbum = null;
	public AddPopupMenuAlbum(List<Album> AlbumList,JTextField txtAlbum){
		MyListener listener = new MyListener();
		for (Album s:AlbumList){
			JMenuItem mnit = new JMenuItem(s.getAlbumName());
			this.add(mnit);
			mnit.setActionCommand(String.valueOf(s.getAlbumId()));
			mnit.addActionListener(listener);
		}
		this.txtAlbum = txtAlbum;
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem mnit = (JMenuItem)e.getSource();
			int singerId = Integer.parseInt(mnit.getActionCommand());
			txtAlbum.setText(singerId+"."+mnit.getText());
			System.out.println(mnit.getText());
		}
		
	}
}
