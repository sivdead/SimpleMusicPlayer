package cn.com.musicplayerserver.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

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
import cn.com.musicplayerserver.ui.adddata.AddAlbum;
import cn.com.musicplayerserver.ui.adddata.AddMusic;
import cn.com.musicplayerserver.ui.adddata.AddSinger;
import cn.com.musicplayerserver.ui.model.AlbumTableModel;
import cn.com.musicplayerserver.ui.model.MusicTableModel;
import cn.com.musicplayerserver.ui.model.SingerTableModel;
import cn.com.musicplayerserver.util.Utils;
import cn.com.musicplayerserver.vo.AlbumTableVO;
import cn.com.musicplayerserver.vo.MusicTableVO;
import cn.com.musicplayerserver.vo.SingerTableVO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MusicManage extends JPanel{
	private JTextField txtMusic;
	private AddPopupMenu ppmn;
	private JTable table;
	private static MusicManage musicManage = null;
	private JLabel lblPlayList;
	private JComboBox comboBox;
	private MyListener listener = null;
	private JLabel lblMusic;
	private JLabel lblSinger;
	private JLabel lblAlbum;
	private List<Singer> singer = null;
	private List<SingerTableVO> singerVO = null;
	private List<MusicTableVO> musicVOList = null;
	private List<Music> music = null;
	private List<AlbumTableVO> albumVOList = null;
	private List<Album> album = null;
	private IMusicService musicService = null;
	private JButton check;
	public static MusicManage getInstance(){
		if (musicManage == null){
			musicManage = new MusicManage();
		}
		musicManage.repaint();
		return musicManage;
	}
	/**
	 * Create the panel.
	 */
	private MusicManage() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u97F3\u4E50\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, Color.DARK_GRAY));
		this.setLocation(0,0);
		setSize(785,500);
		setLayout(null);
		
		txtMusic = new JTextField();
		txtMusic.setForeground(Color.DARK_GRAY);
		txtMusic.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		txtMusic.setColumns(10);
		txtMusic.setBounds(163, 50, 253, 30);
		add(txtMusic);
		
		listener = new MyListener();
		
		lblMusic = new JLabel("\u6B4C\u66F2");
		lblMusic.setForeground(Color.DARK_GRAY);
		lblMusic.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		lblMusic.setHorizontalAlignment(SwingConstants.CENTER);
		lblMusic.setBounds(37, 116, 54, 23);
		lblMusic.setName("music");
		lblMusic.addMouseListener(listener);
		add(lblMusic);
		
		lblSinger = new JLabel("\u6B4C\u624B");
		lblSinger.setForeground(Color.DARK_GRAY);
		lblSinger.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		lblSinger.setHorizontalAlignment(SwingConstants.CENTER);
		lblSinger.setBounds(37, 149, 54, 23);
		lblSinger.setName("singer");
		lblSinger.addMouseListener(listener);
		add(lblSinger);
		
		lblAlbum = new JLabel("\u4E13\u8F91");
		lblAlbum.setForeground(Color.DARK_GRAY);
		lblAlbum.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		lblAlbum.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlbum.setBounds(37, 182, 54, 23);
		lblAlbum.setName("album");
		lblAlbum.addMouseListener(listener);
		add(lblAlbum);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(""));
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setBounds(101, 110, 578, 344);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3){
					Object obj = ((MusicTableModel)table.getModel()).getMusic(table.rowAtPoint(table.getMousePosition()));
					AddPopupMenu menu = new AddPopupMenu(table.getMousePosition());
					menu.show(table,e.getX(), e.getY());
//			
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("\u6DFB\u52A0");
		btnAdd.setForeground(Color.DARK_GRAY);
		btnAdd.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		btnAdd.setBounds(210, 467, 75, 30);
		add(btnAdd);
		
		JButton btnEdit = new JButton("\u7F16\u8F91");
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		btnEdit.setBounds(334, 467, 75, 30);
		add(btnEdit);
		
		JButton btnDel = new JButton("\u5220\u9664");
		btnDel.setForeground(Color.DARK_GRAY);
		btnDel.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		btnDel.setBounds(462, 467, 75, 30);
		add(btnDel);
		
		lblPlayList = new JLabel("\u6B4C\u5355");
		lblPlayList.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayList.setForeground(Color.DARK_GRAY);
		lblPlayList.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		lblPlayList.setBounds(37, 215, 54, 23);
		add(lblPlayList);
		lblPlayList.addMouseListener(listener);
		lblPlayList.setName("playList");
		

		comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 15));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(462, 49, 82, 30);
		String[] str = {"  ≤È—Ø","≤È—Ø∏Ë«˙","≤È—Ø∏Ë ÷","≤È—Ø◊®º≠"};
		for (String st:str){
			comboBox.addItem(st);
		}
		add(comboBox);
		
		check = new JButton("\u67E5 \u8BE2");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicVOList = null;
				singerVO = null;
				albumVOList = null;
				int index = -1;
				index = comboBox.getSelectedIndex();
				musicService = ServiceFactory.createMusicService();

				if(index==0){
					singer = musicService.searchSinger(txtMusic.getText());
					SingerTableVO vo = null;
					singerVO = new ArrayList<SingerTableVO>();
					if (singer != null){
						System.out.println(singer.isEmpty());
					for(Singer m:singer){
						vo = new SingerTableVO();
						ISystemParamDAO sys = DAOFactory.createSystemParamDAO();
						SystemParam sysPa = sys.getSystemParamById(m.getNationId());
						if (sysPa != null){
							vo.setNation(sysPa.getValue());
						}
						sysPa = null;
						sysPa = sys.getSystemParamById(m.getSexId());
						if (sysPa != null)
							vo.setSex(sysPa.getValue());
						vo.setSingerName(m.getSingerName());
						singerVO.add(vo);
					}
					}
					music = null;
					music = musicService.searchMusic(txtMusic.getText());
					MusicTableVO musicVO = null;
					musicVOList = new ArrayList<MusicTableVO>();
					if (music != null){
						System.out.println(music.isEmpty());
					for(Music m:music){
						musicVO = new MusicTableVO();
						ISingerDAO sys = DAOFactory.createSingerDAO();
						Singer singer = sys.getSingerBySingerId(m.getSingerId());
						IAlbumDAO alm = DAOFactory.createAlbumDAO();
//						System.out.println("album:"+m.getAlbumId());
						Album al = alm.getAlbumByAlbumId(m.getAlbumId());
						if (al != null){
							musicVO.setAlbum(al.getAlbumName());
						}
						musicVO.setMusicName(m.getMusicName());
						musicVO.setSinger(singer.getSingerName());
						musicVO.setLength(Utils.getTime(m.getMusicLength()));
						musicVOList.add(musicVO);
					}
					}
					album = musicService.searchAlbum(txtMusic.getText());
					AlbumTableVO albumVO = null;
					albumVOList = new ArrayList<AlbumTableVO>();
					if (album != null){
						System.out.println(album.isEmpty());
					for(Album m:album){
						albumVO = new AlbumTableVO();
						ISingerDAO singerDAO = DAOFactory.createSingerDAO();
						Singer singer = singerDAO.getSingerBySingerId(m.getSingerId());
						albumVO.setAlbumName(m.getAlbumName());
						albumVO.setSinger(singer.getSingerName());
						albumVOList.add(albumVO);
					}
					}
				}else if(index==1){
					music = null;
					music = musicService.searchMusic(txtMusic.getText());
					MusicTableVO musicVO = null;
					musicVOList = new ArrayList<MusicTableVO>();
					if (music != null)
					for(Music m:music){
						musicVO = new MusicTableVO();
						ISingerDAO sys = DAOFactory.createSingerDAO();
						Singer sysPa = sys.getSingerBySingerId(m.getSingerId());
						IAlbumDAO alm = DAOFactory.createAlbumDAO();
						Album al = alm.getAlbumByAlbumId(m.getAlbumId());
						musicVO.setAlbum(al.getAlbumName());
						musicVO.setMusicName(m.getMusicName());
						musicVO.setSinger(sysPa.getSingerName());
						musicVO.setLength(m.getMusicLength().toString());
						System.out.println(musicVO.getSinger());
						musicVOList.add(musicVO);
					}
				}else if(index==2){
					singer = musicService.searchSinger(txtMusic.getText());
					SingerTableVO vo = null;
					singerVO = new ArrayList<SingerTableVO>();
					if (singer != null)
					for(Singer m:singer){
						vo = new SingerTableVO();
						ISystemParamDAO sys = DAOFactory.createSystemParamDAO();
						SystemParam sysPa = sys.getSystemParamById(m.getNationId());
						if (sysPa != null){
							vo.setNation(sysPa.getValue());
						}
						sysPa = sys.getSystemParamById(m.getSexId());
						if (sysPa != null)
							vo.setSex(sysPa.getValue());
						vo.setSingerName(m.getSingerName());
						singerVO.add(vo);
					}
				}else{
					album = musicService.searchAlbum(txtMusic.getText());
					AlbumTableVO albumVO = null;
					albumVOList = new ArrayList<AlbumTableVO>();
					if (album != null)
					for(Album m:album){
						albumVO = new AlbumTableVO();
						ISingerDAO sys = DAOFactory.createSingerDAO();
						Singer sysPa = sys.getSingerBySingerId(m.getSingerId());
						albumVO.setAlbumName(m.getAlbumName());
						albumVO.setSinger(sysPa.getSingerName());
						albumVOList.add(albumVO);
					}
				}
			}
			
		});
		check.setBounds(574, 50, 82, 27);
		add(check);
	}
	class MyListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			JLabel lbl = (JLabel)e.getSource();
			switch(lbl.getName()){
			case "music":
				MusicTableModel musicModel = new MusicTableModel();
				musicModel.setMusicList(musicVOList);
				table.setModel(musicModel);
				break;
			case "album":
				AlbumTableModel albumModel = new AlbumTableModel();
				albumModel.setAlbumList(albumVOList);
				table.setModel(albumModel);
				break;
			case "singer":
				SingerTableModel singerModel = new SingerTableModel();
				singerModel.setSingerList(singerVO);
				table.setModel(singerModel);
				break;
			}
		}
	}
	class TableListener extends MouseAdapter{


		@Override
		public void mouseClicked(MouseEvent e) {
			JTable tbl = (JTable)e.getSource();
			System.out.println(tbl.getModel().getColumnName(1));
			switch(tbl.getModel().getColumnName(1)){
			case "∏Ë√˚":
				AddMusic addMusic = AddMusic.getInstance();
				addMusic.setVisible(true);
				break;
			case "∏Ë ÷":
				break;
			case "◊®º≠":
				break;
			}
		}
	}
}
