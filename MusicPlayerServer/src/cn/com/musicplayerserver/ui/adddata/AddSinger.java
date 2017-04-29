package cn.com.musicplayerserver.ui.adddata;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.sun.org.apache.xml.internal.security.Init;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.ISystemParamDAO;
import cn.com.musicplayerserver.entity.Singer;
import cn.com.musicplayerserver.entity.SystemParam;
import cn.com.musicplayerserver.service.IMusicService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.util.Tools;

import javax.swing.UIManager;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class AddSinger extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtBirth;
	private JRadioButton rdoBoy;
	private String[] nation = { "内地", "港台", "韩国", "欧美", "日本", "其它" };
	private JRadioButton rdoGirl;
	private JTextArea txtaIntro;
	private JButton btnSubmit;
	private ButtonGroup sex;
	private static AddSinger addSinger = null;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddSinger frame = new AddSinger();
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
	public static AddSinger getInstance() {
		if (addSinger != null) {
			addSinger.dispose();
		}
		addSinger = new AddSinger();
		return addSinger;
	}

	private AddSinger() {
		this.setLocationRelativeTo(null);
		setTitle("\u6DFB\u52A0\u6B4C\u624B");
		setBounds(100, 100, 417, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtName = new JTextField();
		txtName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtName.setForeground(Color.BLACK);
		txtName.setBounds(115, 46, 68, 25);
		contentPane.add(txtName);
		txtName.setColumns(10);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		lblNewLabel.setBounds(37, 46, 68, 25);
		contentPane.add(lblNewLabel);

		btnSubmit = new JButton("\u63D0\u4EA4");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 获取页面信息
				String name = txtName.getText();
				String sexx = sex.getSelection().getActionCommand();
				int sexxId = -1;
				ISystemParamDAO sys = DAOFactory.createSystemParamDAO();
				SystemParam sysPa = sys.getSystemParamByNameAndValue("性别", sexx);
				sexxId = sysPa.getId();
				String birth = txtBirth.getText();
				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date bir = null;
				try {
					bir = date.parse(birth);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				Date dat = new Date(bir.getTime());
				String come = comboBox.getSelectedItem().toString();
				sysPa = sys.getSystemParamByNameAndValue("国籍", come);
				String area = txtaIntro.getText();
				// 简单验证
				if (Tools.isEmpty(name) || Tools.isEmpty(birth)) {
					JOptionPane.showMessageDialog(null, "歌手名，歌手出生日期不能为空");
					throw new RuntimeException("歌手名，歌手出生日期不能为空");
				} else {
					Singer singer = new Singer();
					singer.setBirthday(dat);
					singer.setSexId(sexxId);
					singer.setSingerName(name);
					singer.setNationId(sysPa.getId());
					singer.setIntro(area);
					// 调用IMusicService
					IMusicService musicService = ServiceFactory.createMusicService();
					boolean bool = musicService.addSinger(singer);
					if (bool) {
						JOptionPane.showMessageDialog(null, "歌手添加成功");
					} else {
						JOptionPane.showMessageDialog(null, "歌手添加不成功");
						throw new RuntimeException("歌手添加不成功");
					}
				}
			}
		});
		btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnSubmit.setForeground(Color.DARK_GRAY);
		btnSubmit.setBounds(155, 412, 73, 23);
		contentPane.add(btnSubmit);

		JLabel label = new JLabel("\u6027\u522B");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setForeground(Color.BLACK);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(37, 84, 68, 25);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u51FA\u751F\u65E5\u671F");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(37, 122, 68, 25);
		contentPane.add(label_1);

		txtBirth = new JTextField();
		txtBirth.setForeground(Color.BLACK);
		txtBirth.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtBirth.setColumns(10);
		txtBirth.setBounds(115, 123, 90, 25);
		contentPane.add(txtBirth);

		JLabel label_2 = new JLabel("\u56FD\u7C4D");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_2.setBounds(37, 160, 68, 28);
		contentPane.add(label_2);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7B80\u4ECB",
				TitledBorder.LEADING, TitledBorder.TOP, new Font("微软雅黑", Font.PLAIN, 14), Color.BLACK));
		panel.setBounds(37, 198, 342, 201);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		txtaIntro = new JTextArea();
		txtaIntro.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		panel.add(txtaIntro, BorderLayout.CENTER);

		rdoBoy = new JRadioButton("\u7537");
		rdoBoy.setBounds(113, 84, 45, 25);
		rdoBoy.getModel().setActionCommand("男");
		contentPane.add(rdoBoy);

		rdoGirl = new JRadioButton("\u5973");
		rdoGirl.setBounds(160, 84, 45, 25);
		rdoGirl.getModel().setActionCommand("女");

		sex = new ButtonGroup();
		sex.add(rdoBoy);
		sex.add(rdoGirl);
		rdoBoy.setSelected(true);
		contentPane.add(rdoGirl);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(nation));
		comboBox.setBounds(115, 161, 90, 28);
		comboBox.getSelectedIndex();
		contentPane.add(comboBox);
	}
}
