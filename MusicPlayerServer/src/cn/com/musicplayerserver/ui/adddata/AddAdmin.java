package cn.com.musicplayerserver.ui.adddata;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cn.com.musicplayerserver.exception.UserExistException;
import cn.com.musicplayerserver.service.IAuthorityControlService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.MyPasswordField;
import cn.com.musicplayerserver.ui.MyTextField;
import cn.com.musicplayerserver.util.Tools;
import cn.com.musicplayerserver.vo.ManageAddVO;

public class AddAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtAccount;
	private static AddAdmin addAdmin = null;
	private MyTextField txtMail;
	private JPasswordField txtRePsw;
	private JPasswordField txtPsw;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddAdmin frame = new AddAdmin();
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
	public static AddAdmin getInstance(){
		if (addAdmin != null){
			addAdmin.dispose();
		}
		addAdmin = new AddAdmin();
		return addAdmin;
	}
	private  AddAdmin() {
		setBounds(100, 100, 317, 379);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAccount = new MyTextField();
		txtAccount.setText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		txtAccount.setForeground(Color.LIGHT_GRAY);
		txtAccount.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtAccount.setColumns(10);
		txtAccount.setBounds(39, 94, 212, 36);
		getContentPane().add(txtAccount);
		
		txtRePsw = new MyPasswordField();
		txtRePsw.setText("\u8BF7\u91CD\u65B0\u8F93\u5165\u5BC6\u7801");
		txtRePsw.setForeground(Color.LIGHT_GRAY);
		txtRePsw.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtRePsw.setColumns(10);
		txtRePsw.setBounds(39, 186, 212, 36);
		contentPane.add(txtRePsw);
		
		txtMail = new MyTextField();
		txtMail.setText("\u8BF7\u8F93\u5165\u90AE\u7BB1");
		txtMail.setForeground(Color.LIGHT_GRAY);
		txtMail.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtMail.setColumns(10);
		txtMail.setBounds(39, 232, 212, 36);
		contentPane.add(txtMail);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取页面信息
				String userName = txtAccount.getText();
	     		String password = new String(txtPsw.getPassword());
	     		String rePassword = txtRePsw.getPassword().toString();
	     		String mail = txtMail.getText();
	     		if(Tools.isEmpty(userName)||Tools.isEmpty(password)||Tools.isEmpty(rePassword)||Tools.isEmpty(mail)){
	     			JOptionPane.showMessageDialog(null, "账号，密码，确认密码，邮箱不能为空");
	     			throw new RuntimeException("账号，密码，确认密码，邮箱不能为空");
	     		}
	     		ManageAddVO vo = new ManageAddVO();
	     		vo.setUserName(userName);
	     		vo.setPsw(password);
	     		vo.setRePsw(rePassword);
	     		vo.setMail(mail);
	     		IAuthorityControlService controlService = ServiceFactory.createAuthorityControlService();
	     		try {
					boolean bool = controlService.addAdmin(vo);
					if(bool){
						JOptionPane.showMessageDialog(null, "管理员建立成功");
					}
				} catch (UserExistException e1) {
					JOptionPane.showMessageDialog(null, "管理员名已存在，请重新命名");
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(95, 290, 93, 23);
		contentPane.add(button);
		
		txtPsw = new MyPasswordField();
		txtPsw.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		txtPsw.setForeground(Color.LIGHT_GRAY);
		txtPsw.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtPsw.setColumns(10);
		txtPsw.setBounds(39, 140, 212, 36);
		contentPane.add(txtPsw);
	}
}
