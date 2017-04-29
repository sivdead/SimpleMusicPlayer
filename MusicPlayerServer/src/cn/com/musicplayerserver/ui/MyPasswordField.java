package cn.com.musicplayerserver.ui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPasswordField;

public class MyPasswordField extends JPasswordField {
	private Boolean keyTyped;
	public MyPasswordField(){
		super();
		keyTyped = false;
		this.setEchoChar((char) 0);
		this.addKeyListener(new KeyAdapter(){

			@Override
			public void keyTyped(KeyEvent e) {
				if (!keyTyped){
					keyTyped = true;;
					MyPasswordField txt = (MyPasswordField)e.getSource();
					txt.setForeground(Color.DARK_GRAY);
					txt.setText("");
					setEchoChar('*');
				}
			}
			
		});
	}
}
