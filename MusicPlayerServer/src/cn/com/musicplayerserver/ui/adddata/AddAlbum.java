package cn.com.musicplayerserver.ui.adddata;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IAlbumDAO;
import cn.com.musicplayerserver.dao.ISingerDAO;
import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Singer;
import cn.com.musicplayerserver.service.IMusicService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.AddPopupMenu;
import cn.com.musicplayerserver.ui.MyPanel;
import cn.com.musicplayerserver.util.Tools;

public class AddAlbum extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JLabel label;
	private JTextField txtSinger;
	private JButton btnQuery;
	private JPanel panel;
	private JTextArea textArea;
	private JButton txtButton;
	private JLabel label_1;
	private JButton button_2;
	private JPanel txtPanel;
	private static AddAlbum addAlbum = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddAlbum frame = new AddAlbum();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public static AddAlbum getInstance(){
		if (addAlbum != null){
			addAlbum.dispose();
		}
		addAlbum = new AddAlbum();
		return addAlbum;
	}
	private  AddAlbum() {
		setTitle("\u6DFB\u52A0\u4E13\u8F91");
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 600, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMingcheng = new JLabel("\u540D\u79F0");
		lblMingcheng.setHorizontalAlignment(SwingConstants.LEFT);
		lblMingcheng.setForeground(Color.BLACK);
		lblMingcheng.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblMingcheng.setBounds(65, 74, 46, 25);
		contentPane.add(lblMingcheng);
		
		txtName = new JTextField();
		txtName.setForeground(Color.BLACK);
		txtName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(121, 74, 68, 25);
		contentPane.add(txtName);
		
		label = new JLabel("\u6B4C\u624B");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(65, 109, 46, 25);
		contentPane.add(label);
		
		txtSinger = new JTextField();
		txtSinger.setForeground(Color.BLACK);
		txtSinger.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtSinger.setColumns(10);
		txtSinger.setBounds(121, 109, 68, 25);
		contentPane.add(txtSinger);
		
		btnQuery = new JButton("\u67E5\u8BE2");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String singer = txtSinger.getText();
				//创建IALBUMDAO
				ISingerDAO singerDAO = DAOFactory.createSingerDAO();
				List<Singer> sing = singerDAO.selectSinger(singer);
				//1.获取歌手列表singerList
				AddPopupMenu pop = new AddPopupMenu(sing,txtSinger);
				pop.show(btnQuery,62,25);
			}
		});
		btnQuery.setBounds(199, 110, 62, 25);
		contentPane.add(btnQuery);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u7B80\u4ECB", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(52, 166, 438, 208);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		textArea = new JTextArea();
		panel.add(textArea, BorderLayout.CENTER);
		
		txtButton = new JButton("\u63D0\u4EA4");
		txtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int singerId = Integer.parseInt(txtSinger.getText().substring(0,txtSinger.getText().indexOf(".")));
				String singerName = txtSinger.getText().substring(txtSinger.getText().indexOf(".")+1,txtSinger.getText().length());
				String name = txtName.getText();
				String area = textArea.getText();
				if(Tools.isEmpty(singerName)||Tools.isEmpty(name)){
					JOptionPane.showMessageDialog(null, "歌手和专辑名不能为空");
					throw new RuntimeException("歌手和专辑名不能为空");
				}
				Album album = new Album();
				album.setAlbumName(name);
				album.setSingerId(singerId);
				album.setAlbumDesc(area);
				IMusicService musicService = ServiceFactory.createMusicService();
				boolean bool = musicService.addAlbum(album);
				if(bool){
					JOptionPane.showMessageDialog(null, "添加专辑成功");
				}else{
					JOptionPane.showMessageDialog(null, "没有添加成功");
					throw new RuntimeException("没有添加成功");
				}
			}
		});
		txtButton.setBounds(234, 393, 76, 25);
		contentPane.add(txtButton);
		
		label_1 = new JLabel("\u56FE\u7247");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(274, 74, 46, 25);
		contentPane.add(label_1);
		
		button_2 = new JButton("...");
		button_2.setBounds(330, 75, 30, 25);
		contentPane.add(button_2);
		
		txtPanel = new MyPanel(new ImageIcon("D:\\Documents\\Pictures\\b23eada4166be28dcb0a4695797952bb.jpg").getImage());
		txtPanel.setBounds(370, 45, 120, 111);
		contentPane.add(txtPanel);
	}

}
