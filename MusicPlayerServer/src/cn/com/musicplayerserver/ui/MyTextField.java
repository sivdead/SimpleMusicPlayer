package cn.com.musicplayerserver.ui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class MyTextField extends JTextField {
	private Boolean keyTyped;
	public MyTextField(){
		super();
		keyTyped = false;
		this.addKeyListener(new KeyAdapter(){

			@Override
			public void keyTyped(KeyEvent e) {
				if (!keyTyped){
					keyTyped = true;;
					MyTextField txt = (MyTextField)e.getSource();
					txt.setForeground(Color.DARK_GRAY);
					txt.setText("");
				}
			}
			
		});
	}
}
