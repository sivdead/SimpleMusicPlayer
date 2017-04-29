package cn.com.musicplayerserver.ui.adddata;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.media.Manager;
import javax.media.Player;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IAlbumDAO;
import cn.com.musicplayerserver.dao.ISingerDAO;
import cn.com.musicplayerserver.dao.ISystemParamDAO;
import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Music;
import cn.com.musicplayerserver.entity.Singer;
import cn.com.musicplayerserver.entity.SystemParam;
import cn.com.musicplayerserver.service.IMusicService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.AddPopupMenu;
import cn.com.musicplayerserver.ui.AddPopupMenuAlbum;
import cn.com.musicplayerserver.ui.AddPopupMenuSystemParam;
import cn.com.musicplayerserver.ui.model.MusicTableModel;
import cn.com.musicplayerserver.util.Utils;
import cn.com.musicplayerserver.vo.MusicTableVO;

public class AddMusic extends JFrame {
	private JTextField txtMusicName;
	private JTextField txtSinger;
	private JTextField txtAlbum;
	private JTextField txtPath;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private static AddMusic addMusic = null;
	private List<Music> musicList = null;
	private JButton btnSearchSinger;
	private JButton btnSearchAlbum;
	private JButton button;
	private List<MusicTableVO> musicVOList = null;
	MusicTableModel musicModel = null;

//	public static void main(String arg[]) {
//		JFrame frame = new AddMusic();
//		frame.setVisible(true);
//	}

	/**
	 * Create the panel.
	 */
	public static AddMusic getInstance() {
		if (addMusic != null) {
			addMusic.dispose();
		}
		addMusic = new AddMusic();
		return addMusic;
	}

	private AddMusic() {
		this.setLocationRelativeTo(null);
		setTitle("\u6DFB\u52A0\u97F3\u4E50");
		setResizable(false);
		setBackground(Color.WHITE);
		Font font = new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13);
		this.setSize(600, 480);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 564, 421);
		this.setContentPane(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u6B4C\u540D");
		lblNewLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(33, 80, 54, 15);
		getContentPane().add(lblNewLabel);

		txtMusicName = new JTextField();
		txtMusicName.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		txtMusicName.setBounds(97, 77, 66, 21);
		getContentPane().add(txtMusicName);
		txtMusicName.setColumns(10);

		JLabel label = new JLabel("\u6B4C\u624B");
		label.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(33, 121, 54, 15);
		getContentPane().add(label);

		txtSinger = new JTextField();
		txtSinger.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		txtSinger.setColumns(10);
		txtSinger.setBounds(97, 118, 66, 21);
		getContentPane().add(txtSinger);

		JLabel label_1 = new JLabel("\u4E13\u8F91");
		label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(320, 120, 54, 15);
		getContentPane().add(label_1);

		txtAlbum = new JTextField();
		txtAlbum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		txtAlbum.setColumns(10);
		txtAlbum.setBounds(384, 117, 66, 21);
		getContentPane().add(txtAlbum);

		JLabel label_2 = new JLabel("\u8DEF\u5F84");
		label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(33, 156, 54, 15);
		getContentPane().add(label_2);

		btnSearchSinger = new JButton("\u67E5\u8BE2");
		btnSearchSinger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int albumId = 0;
				if(txtAlbum.getText().indexOf(".")!=-1){
				    albumId = Integer.parseInt(txtAlbum.getText().substring(0,txtAlbum.getText().indexOf(".")));
				}
				String singer = txtSinger.getText();
				ISingerDAO singerDAO = DAOFactory.createSingerDAO();
				AddPopupMenu pop = null;
				if (albumId == 0) {
					List<Singer> sing = new ArrayList<Singer>();
					sing = singerDAO.selectSinger(singer);
					pop = new AddPopupMenu(sing, txtSinger);
				} else {
					Singer sing = singerDAO.selectSinger(albumId);
					pop = new AddPopupMenu(sing, txtSinger);
				}
				pop.show(btnSearchSinger,74,23);
			}
		});
		btnSearchSinger.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		btnSearchSinger.setBounds(173, 117, 74, 23);

		getContentPane().add(btnSearchSinger);

		btnSearchAlbum = new JButton("\u67E5\u8BE2");
		btnSearchAlbum.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int singerId = 0;
				String album = txtAlbum.getText();
				if(txtSinger.getText().indexOf(".")!=-1){
				   singerId = Integer.parseInt(txtSinger.getText().substring(0,txtSinger.getText().indexOf(".")));
				}
				IAlbumDAO albumDAO = DAOFactory.createAlbumDAO();
				List<Album> al = new ArrayList<Album>();
				if (singerId == 0) {
					al = albumDAO.selectAlbum(album);
				} else {
					al = albumDAO.selectAlbum(album, singerId);
				}
				AddPopupMenuAlbum pop = new AddPopupMenuAlbum(al, txtAlbum);
				pop.show(btnSearchAlbum,74,23);
			}
		});
		btnSearchAlbum.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		btnSearchAlbum.setBounds(460, 116, 74, 23);
		getContentPane().add(btnSearchAlbum);

		txtPath = new JTextField();
		txtPath.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		txtPath.setColumns(10);
		txtPath.setBounds(97, 153, 141, 21);
		getContentPane().add(txtPath);

		JButton btnPath = new JButton("...");
		btnPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser jfs = new JFileChooser();
				jfs.showDialog(null,"Ñ¡Ôñ");
				File file = jfs.getSelectedFile();
				String fileName = file.getAbsolutePath();
				txtPath.setText(fileName);
			}
		});
		btnPath.setBounds(248, 152, 26, 23);
		getContentPane().add(btnPath);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 184, 491, 187);
		panel.add(scrollPane);

		table = new JTable();
//		MusicTableModel model = new MusicTableModel();
//		model.setMusicList(musicVOList);
//		table.setModel(model);
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (musicList == null){
					musicList = new ArrayList<Music>();
				}
				if (musicVOList == null){
					musicVOList = new ArrayList<MusicTableVO>();
				}
				Music music = new Music();
				music.setMusicName(txtMusicName.getText());
				String url = txtPath.getText();
				int index = url.indexOf("Music");
				url = url.substring(index);
				url = "http://192.168.18.99:8080/"+url;
				url = url.replaceAll(" ", "%20");
				url = url.replaceAll("\\\\","/");
//				System.out.println(url);
				try{
					Player player = Manager.createRealizedPlayer(new URL(url));
					if (player != null){
						music.setMusicLength((int)player.getDuration().getSeconds());
					}
				}catch(Exception e1){
					e1.printStackTrace();
				}
				music.setMusicUrl(url);
				String album = txtAlbum.getText();
				String alb = album.substring(0,album.indexOf("."));
				music.setAlbumId(Integer.parseInt(alb));
				String singer = txtSinger.getText();
				String sing = singer.substring(0,singer.indexOf("."));
				music.setSingerId(Integer.parseInt(sing));
				musicList.add(music);
				MusicTableVO musicVO = new MusicTableVO();
				musicVO.setAlbum(album.substring(album.indexOf(".")+1,album.length()));
				musicVO.setMusicName(txtMusicName.getText());
				musicVO.setSinger(singer.substring(singer.indexOf(".")+1,singer.length()));
				musicVOList.add(musicVO);
				musicModel = new MusicTableModel();
				musicModel.setMusicList(musicVOList);
				table.setModel(musicModel);
			}
		});
		btnAdd.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		btnAdd.setBounds(200, 394, 83, 37);
		panel.add(btnAdd);

		JLabel label_3 = new JLabel("\u98CE\u683C");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_3.setBounds(236, 81, 54, 15);
		panel.add(label_3);

		button = new JButton("\u9009\u62E9");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String  type = textField.getText();
				ISystemParamDAO sys = DAOFactory.createSystemParamDAO();
				List<SystemParam> li = new ArrayList<SystemParam>();
				li = sys.selectSystemParam(type);
				AddPopupMenuSystemParam pop = new AddPopupMenuSystemParam(li, textField);
				pop.show(button,74,23);
			}
		});
		button.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		button.setBounds(460, 77, 74, 23);
		panel.add(button);

		textField = new JTextField();
		textField.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		textField.setColumns(10);
		textField.setBounds(300, 78, 150, 21);
		panel.add(textField);

		JButton btnSubmit = new JButton("\u63D0\u4EA4");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean bool = false;
				IMusicService musicService = ServiceFactory.createMusicService();
				List<Music> musics = new ArrayList<Music>(); 
				musics.addAll(musicList);
				for (Music music:musicList){
					try {
						bool = musicService.uploadMusic(music);
						if(bool){
							JOptionPane.showMessageDialog(null, "¸èÇú"+music.getMusicName()+"Ìí¼Ó³É¹¦");
							musics.remove(music);
						}
					} catch (UnknownHostException e1) {
						JOptionPane.showMessageDialog(null, "¸èÇú"+music+"Ìí¼Ó²»³É¹¦");
						e1.printStackTrace();
					}
				}
				musicModel = (MusicTableModel)table.getModel();
				musicModel.setMusicList(Utils.transferToMusic(musics));
			}
		});
		btnSubmit.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 14));
		btnSubmit.setBounds(300, 394, 83, 37);
		panel.add(btnSubmit);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		textField_1.setColumns(10);
		textField_1.setBounds(357, 153, 141, 21);
		panel.add(textField_1);

		JLabel label_4 = new JLabel("\u56FE\u7247");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 13));
		label_4.setBounds(293, 156, 54, 15);
		panel.add(label_4);

		JButton button_1 = new JButton("...");
		button_1.setBounds(508, 152, 26, 23);
		panel.add(button_1);

	}
}
