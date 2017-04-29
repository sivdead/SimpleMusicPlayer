package cn.com.musicplayerserver.ui;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import cn.com.musicplayerserver.entity.Singer;
import cn.com.musicplayerserver.ui.adddata.AddAlbum;
import cn.com.musicplayerserver.ui.adddata.AddMusic;
import cn.com.musicplayerserver.ui.adddata.AddSinger;

public class AddPopupMenu extends JPopupMenu{
	private JTextField txtSinger = null;
	public AddPopupMenu(Point p){
		Font font = new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 13);
		MyListener l = new MyListener();
		JMenuItem mnitMusic = new JMenuItem("ÃÌº”“Ù¿÷");
		mnitMusic.setFont(font);
		mnitMusic.addActionListener(l);
		this.add(mnitMusic);
		JMenuItem mnitSinger = new JMenuItem("ÃÌº”∏Ë ÷");
		mnitSinger.setFont(font);
		mnitSinger.addActionListener(l);
		this.add(mnitSinger);
		JMenuItem mnitAlbum = new JMenuItem("ÃÌº”◊®º≠");
		mnitAlbum.setFont(font);
		mnitAlbum.addActionListener(l);
		this.add(mnitAlbum);
	}
	public AddPopupMenu(List<Singer> singerList,JTextField txtSinger){
		AlbumListener listener = new AlbumListener();
		for (Singer s:singerList){
			JMenuItem mnit = new JMenuItem(s.getSingerName());
			this.add(mnit);
			mnit.setActionCommand(String.valueOf(s.getSingerId()));
			mnit.addActionListener(listener);
		}
		this.txtSinger = txtSinger;
	}
	public AddPopupMenu(Singer sing,JTextField txtSinger){
		AlbumListener listener = new AlbumListener();
		JMenuItem mnit = new JMenuItem(sing.getSingerName());
		this.add(mnit);
		mnit.setActionCommand(String.valueOf(sing.getSingerId()));
		mnit.addActionListener(listener);;
		this.txtSinger = txtSinger;
	}
	class MyListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem mnit = (JMenuItem)e.getSource();
			String str = mnit.getActionCommand();
			if(str.equals("ÃÌº”“Ù¿÷")){
				AddMusic music = AddMusic.getInstance();
				music.setVisible(true);
			}else if(str.equals("ÃÌº”∏Ë ÷")){
				AddSinger singer = AddSinger.getInstance();
				singer.setVisible(true);
			}else if(str.equals("ÃÌº”◊®º≠")){
				AddAlbum album = AddAlbum.getInstance();
				album.setVisible(true);
			}
		}
		
	}
	class AlbumListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem mnit = (JMenuItem)e.getSource();
			int singerId = Integer.parseInt(mnit.getActionCommand());
			txtSinger.setText(singerId+"."+mnit.getText());
			System.out.println(mnit.getText());
		}
		
	}
}
