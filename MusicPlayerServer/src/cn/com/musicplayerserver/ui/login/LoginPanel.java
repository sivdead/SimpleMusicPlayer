package cn.com.musicplayerserver.ui.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.service.IAuthorityControlService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.LoginUI;
import cn.com.musicplayerserver.ui.MainUI;
import cn.com.musicplayerserver.ui.MyPasswordField;
import cn.com.musicplayerserver.ui.MyTextField;
import cn.com.musicplayerserver.util.Tools;

public class LoginPanel extends JPanel {
	private JTextField txtAccount;
	private JPasswordField txtPsw;
	private JButton lblPswReset;
	private JButton btnLogin;
	private JLabel lblImg;
	private PswResetPanel pnlReset = null;
	/**
	 * Create the panel.
	 */
	public LoginPanel(final LoginUI log) {
		this.setSize(294,411);
		this.setBorder(null);
		this.setBackground(null);
		setLayout(null);	
		txtAccount = new MyTextField();
		//MyKeyListener listener = new MyKeyListener();
		txtAccount.setText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		txtAccount.setForeground(Color.LIGHT_GRAY);
		txtAccount.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		txtAccount.setBounds(38, 203, 212, 36);
		//txtAccount.addKeyListener(listener);
		add(txtAccount);
		txtAccount.setColumns(10);
		
		txtPsw = new MyPasswordField();
		//txtPsw.addKeyListener(listener);
		txtPsw.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		txtPsw.setBackground(Color.WHITE);
		txtPsw.setForeground(Color.LIGHT_GRAY);
		txtPsw.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 14));
		txtPsw.setBounds(38, 259, 212, 36);
		add(txtPsw);
		txtPsw.setColumns(10);
		
		lblPswReset = new JButton("\u91CD\u8BBE\u5BC6\u7801");
		lblPswReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.getPnlLog().setVisible(false);
				PswResetPanel pnlReset = new PswResetPanel(log);
				log.setPnlReset(pnlReset);
				log.getPanel().add(pnlReset);
				log.getPnlReset().setVisible(true);
			}
		});
		lblPswReset.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		lblPswReset.setBounds(158, 330, 81, 36);
		add(lblPswReset);
		
		lblImg = new JLabel("");
		lblImg.setBackground(Color.WHITE);
		lblImg.setBounds(38, 10, 212, 160);
		add(lblImg);
		
		btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ªÒ»° ‰»Î’À∫≈£¨√‹¬Î
				String userName = txtAccount.getText();
				String password = new String(txtPsw.getPassword());
				//ºÚµ•—È÷§
				if(Tools.isEmpty(userName)||Tools.isEmpty(password)){
					JOptionPane.showMessageDialog(null, "’À∫≈∫Õ√‹¬Î≤ªƒ‹Œ™ø’");
					throw new RuntimeException("’À∫≈∫Õ√‹¬Î≤ªƒ‹Œ™ø’");
				}
				//µ˜”√IAuthorityControlService
				IAuthorityControlService controlService = ServiceFactory.createAuthorityControlService();
				try {
					boolean bool = controlService.verifyUser(userName, password);
					if(bool){
						JOptionPane.showMessageDialog(null, "πßœ≤µ«¬Ω≥…π¶");
						MainUI main = new MainUI(userName);
						main.setVisible(true);
						log.dispose();
					}
				} catch (UserNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "’À∫≈√ª”–’“µΩ");
					e1.printStackTrace();
				} catch (PasswordErrorException e1) {
					JOptionPane.showMessageDialog(null, "√‹¬Î¥ÌŒÛ");
					e1.printStackTrace();
				} catch (RuntimeException e1) {
					JOptionPane.showMessageDialog(null, "‘À––“Ï≥£");
					e1.printStackTrace();
				}
			}
		});
		btnLogin.setFont(new Font("Œ¢»Ì—≈∫⁄", Font.PLAIN, 12));
		btnLogin.setBounds(47, 330, 81, 36);
		add(btnLogin);
	}
	/*class  MyKeyListener extends KeyAdapter{

		@Override
		public void keyTyped(KeyEvent e) {
			JTextField txt = (JTextField)e.getSource();
			if (txt == txtAccount){
				if (!accountTyped){
					accountTyped = true;
					txt.setForeground(Color.DARK_GRAY);
					txt.setText("");
				}
			}else if (txt == txtPsw){
				if (!pswTyped){
					pswTyped = true;
					txt.setForeground(Color.DARK_GRAY);
					txt.setText("");
				}
			}else if(txt == txtPswRepeat){
				if (!pswRepeatTyped){
					pswRepeatTyped = true;
					txt.setForeground(Color.DARK_GRAY);
					txt.setText("");
				}
			}
		}
		
	}*/
}

