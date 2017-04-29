package cn.com.musicplayerserver.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.service.impl.MessageService;
import cn.com.musicplayerserver.ui.adddata.AddAdmin;
import cn.com.musicplayerserver.ui.adddata.AddAlbum;
import cn.com.musicplayerserver.ui.adddata.AddMusic;
import cn.com.musicplayerserver.ui.adddata.AddSinger;

public class MainUI extends JFrame {

	private JPanel contentPane;
	private JMenuItem mnitMusicManage;
	private JMenuItem mnitVersionInfo;
	private MusicManage musicManage;
	private JPanel panel;
	private AddMusic addMusic;
	private AddSinger addSinger;
	private AddAlbum addAlbum;
	private UserManage userManage;
	public static Manager currUser = null;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// MainUI frame = new MainUI();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	public MainUI(String str) {
		this();
		currUser = new Manager();
		currUser.setUserName(str);
		this.setTitle(str);
	}

	/**
	 * Create the frame.
	 */
	public MainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnData = new JMenu("\u6570\u636E\u7BA1\u7406");
		menuBar.add(mnData);

		MyListener listener = new MyListener();

		mnitMusicManage = new JMenuItem("\u67E5\u8BE2\u6B4C\u66F2");
		mnData.add(mnitMusicManage);
		mnitMusicManage.setActionCommand("歌曲管理");
		mnitMusicManage.addActionListener(listener);

		JMenuItem mnitAddMusic = new JMenuItem("\u6DFB\u52A0\u6B4C\u66F2");
		mnData.add(mnitAddMusic);
		mnitAddMusic.setActionCommand("添加歌曲");
		mnitAddMusic.addActionListener(listener);

		JMenuItem mnitAddAlbum = new JMenuItem("\u6DFB\u52A0\u4E13\u8F91");
		mnData.add(mnitAddAlbum);
		mnitAddAlbum.setActionCommand("添加专辑");
		mnitAddAlbum.addActionListener(listener);

		JMenuItem mnitAddSinger = new JMenuItem("\u6DFB\u52A0\u6B4C\u624B");
		mnData.add(mnitAddSinger);
		mnitAddSinger.setActionCommand("添加歌手");
		mnitAddSinger.addActionListener(listener);

		JMenuItem menuItem = new JMenuItem("");
		menuItem.setEnabled(false);
		mnData.add(menuItem);

		JMenuItem menuItem_2 = new JMenuItem("\u9000\u51FA");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnData.add(menuItem_2);

		JMenu mnUser = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(mnUser);

		JMenuItem mnitAddAdmin = new JMenuItem("\u6DFB\u52A0\u7BA1\u7406\u5458");
		mnUser.add(mnitAddAdmin);
		mnitAddAdmin.setActionCommand("添加管理员");
		mnitAddAdmin.addActionListener(listener);

		JMenuItem mnitSearchUser = new JMenuItem("\u67E5\u8BE2\u7528\u6237");
		mnUser.add(mnitSearchUser);
		mnitSearchUser.setActionCommand("查询用户");
		mnitSearchUser.addActionListener(listener);

		JMenu mnHelp = new JMenu("\u5E2E\u52A9");
		menuBar.add(mnHelp);

		JMenu mnVersion = new JMenu("\u7248\u672C\u4FE1\u606F");
		mnHelp.add(mnVersion);

		mnitVersionInfo = new JMenuItem("V1.0");
		mnVersion.add(mnitVersionInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		musicManage = MusicManage.getInstance();
		panel.add(musicManage);
		musicManage.setVisible(true);
		
		MessageService msgService = new MessageService();
		msgService.start();
	}

	class MyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getActionCommand());
			switch (e.getActionCommand()) {
			case "歌曲管理":
				panel.removeAll();
				musicManage = MusicManage.getInstance();
				musicManage.setVisible(true);
				panel.add(musicManage);
				panel.repaint();
				break;
			case "添加歌曲":
				addMusic = AddMusic.getInstance();
				addMusic.setVisible(true);
				break;
			case "添加歌手":
				addSinger = AddSinger.getInstance();
				addSinger.setVisible(true);
				break;
			case "添加专辑":
				addAlbum = AddAlbum.getInstance();
				addAlbum.setVisible(true);
				break;
			case "添加管理员":
				IManagerDAO mana = DAOFactory.createManagerDAO();
				Manager manag = mana.findManagerByName(currUser.getUserName());
				int usertpye = manag.getUserType();
				if (usertpye == 3) {
					AddAdmin addAdmin = AddAdmin.getInstance();
					addAdmin.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "权限不足");
				}
				break;
			case "查询用户":
				panel.removeAll();
				userManage = UserManage.getInstance();
				userManage.setVisible(true);
				panel.add(userManage);
				panel.repaint();
				break;
			}
		}

	}
}
