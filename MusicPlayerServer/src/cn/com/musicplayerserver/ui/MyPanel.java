package cn.com.musicplayerserver.ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
	private Image image;
	public MyPanel(Image image){
		super();
		this.image = image;
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(this.image != null){
		// TODO Auto-generated method stub
			g.drawImage(image, 0,0,this.getWidth(), this.getHeight(), null);
		}
	}
}
