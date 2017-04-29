package cn.com.musicplayerserver.ui.login;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPasswordField;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.exception.MailErrorException;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.PasswordNotEqualsException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.service.IAuthorityManageService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.LoginUI;
import cn.com.musicplayerserver.ui.MyPasswordField;
import cn.com.musicplayerserver.ui.MyTextField;
import cn.com.musicplayerserver.util.Tools;
import cn.com.musicplayerserver.vo.PswResetVO;

public class PswResetPanel extends JPanel {
	private JTextField txtAccount;
	private JPasswordField txtPsw;
	private JButton btnSubmit;
	private JButton btnReturn;
	private JLabel lblForgetPsw;
	private JPasswordField txtNewPsw;
	private JPasswordField txtNewPswRepeat;
	/**
	 * Create the panel.
	 */
	public PswResetPanel(final LoginUI log) {
		this.setSize(294,411);
		this.setBorder(null);
		this.setBackground(Color.WHITE);
		setLayout(null);	
		
		txtAccount = new MyTextField();
		//MyKeyListener listener = new MyKeyListener();
		txtAccount.setText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		txtAccount.setForeground(Color.LIGHT_GRAY);
		txtAccount.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtAccount.setBounds(40, 96, 212, 36);
		//txtAccount.addKeyListener(listener);
		add(txtAccount);
		txtAccount.setColumns(10);
		
		txtPsw = new MyPasswordField();
		//txtPsw.addKeyListener(listener);
		txtPsw.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		txtPsw.setBackground(Color.WHITE);
		txtPsw.setForeground(Color.LIGHT_GRAY);
		txtPsw.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtPsw.setBounds(40, 142, 212, 36);
		add(txtPsw);
		txtPsw.setColumns(10);
		
		lblForgetPsw = new JLabel("\u5FD8\u8BB0\u5BC6\u7801?");
		lblForgetPsw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgetPswUI forgetPswUI = new ForgetPswUI();
				forgetPswUI.setVisible(true);
			}
		});
		lblForgetPsw.setForeground(Color.DARK_GRAY);
		lblForgetPsw.setFont(new Font("SimSun", Font.PLAIN, 12));
		lblForgetPsw.setBounds(198, 280, 54, 15);
		add(lblForgetPsw);
		
		btnReturn = new JButton("\u8FD4\u56DE");
		btnReturn.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.getPnlLog().setVisible(true);
				log.getPnlReset().setVisible(false);
				log.setPnlReset(null);
			}
		});
		btnReturn.setForeground(Color.DARK_GRAY);
		btnReturn.setBackground(new Color(238, 232, 170));
		btnReturn.setBounds(160, 305, 68, 36);
		add(btnReturn);
		
		
		btnSubmit = new JButton("\u63D0\u4EA4");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取页面数据
				String userName = txtAccount.getText();
				String psw = new String(txtPsw.getPassword());
				String newPsw = new String(txtNewPsw.getPassword());
				String reNewPsw = new String(txtNewPswRepeat.getPassword());
				//验证输入参数
				if(Tools.isEmpty(userName)||Tools.isEmpty(psw)||Tools.isEmpty(newPsw)||Tools.isEmpty(reNewPsw)){
					JOptionPane.showMessageDialog(null, "参数不能为空");
					throw new RuntimeException("输入参数不能为空");
				}
				PswResetVO vo = new PswResetVO();
				vo.setUserName(userName);
				vo.setPassword(psw);
				vo.setNewPsw(newPsw);
				vo.setReNewPsw(reNewPsw);
				//获取IAuthorityManageService
				IAuthorityManageService manageService = ServiceFactory.createAuthorityManageService();
				try {
					boolean bool = manageService.resetPassword(vo);
					if(bool){
						JOptionPane.showMessageDialog(null, "恭喜您密码更改成功！！！");
					}
				} catch (UserNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "账户名没有找到");
					e1.printStackTrace();
				} catch (PasswordErrorException e1) {
					JOptionPane.showMessageDialog(null, "密码错误");
					e1.printStackTrace();
				} catch (PasswordNotEqualsException e1) {
					JOptionPane.showMessageDialog(null, "新密码和旧密码不等");
					e1.printStackTrace();
				}
			}
		});
		btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnSubmit.setForeground(Color.DARK_GRAY);
		btnSubmit.setBackground(new Color(238, 232, 170));
		btnSubmit.setBounds(64, 305, 68, 36);
		add(btnSubmit);
		
		txtNewPsw = new MyPasswordField();
		txtNewPsw.setText("\u8BF7\u8F93\u5165\u65B0\u5BC6\u7801");
		txtNewPsw.setForeground(Color.LIGHT_GRAY);
		txtNewPsw.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtNewPsw.setColumns(10);
		txtNewPsw.setBackground(Color.WHITE);
		txtNewPsw.setBounds(40, 188, 212, 36);
		add(txtNewPsw);
		
		txtNewPswRepeat = new MyPasswordField();
		txtNewPswRepeat.setText("\u8BF7\u518D\u6B21\u8F93\u5165\u65B0\u5BC6\u7801");
		txtNewPswRepeat.setForeground(Color.LIGHT_GRAY);
		txtNewPswRepeat.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtNewPswRepeat.setColumns(10);
		txtNewPswRepeat.setBackground(Color.WHITE);
		txtNewPswRepeat.setBounds(40, 234, 212, 36);
		add(txtNewPswRepeat);
	}
	/*class  MyKeyListener extends KeyAdapter{

		@Override
		public void keyTyped(KeyEvent e) {
			JTextField txt = (JTextField)e.getSource();
			Boolean turn = false;
			if (txt == txtAccount){
				if (!accountTyped){
					accountTyped = true;
					turn = true;
				}
			}else if (txt == txtPsw){
				if (!pswTyped){
					pswTyped = true;
					turn = true;
				}
			}else if(txt  ==  txtNewPsw){
				if (!newPswTyped){
					newPswTyped = true;
					turn = true;
				}
			}else if(txt == txtNewPswRepeat){
				if (!newPswRepeatTyped){
					newPswRepeatTyped = true;
					turn = true;
				}
				if (turn){
					txt.setForeground(Color.DARK_GRAY);
					txt.setText("");
				}
			}
		}
		
	}*/
}
