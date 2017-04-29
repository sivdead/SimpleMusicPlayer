package cn.com.musicplayerserver.ui.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.service.IMailService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.MyPasswordField;
import cn.com.musicplayerserver.ui.MyTextField;
import cn.com.musicplayerserver.util.Constant;


public class ForgetPswUI extends JFrame {
	private JTextField txtAccount;
	private JTextField txtMail;
	private JTextField txtCode;
	private JPasswordField txtNewPsw;
	private String verifyCode;
	private Manager user;
	
	private final Color pnlColor = Constant.pnlColor;
	private final Color fontColor = Constant.fontColor;
	
	private ForgetPswUI fgp =  null;
	public static void main(String[] args){
	ForgetPswUI ui = new ForgetPswUI();
	ui.setVisible(true);
}
	/**
	 * Create the panel.
	 */
	public ForgetPswUI() {
		setResizable(false);
		this.setSize(280, 380);
		this.setLocationRelativeTo(null);
		setBackground(pnlColor);
		getContentPane().setBackground(pnlColor);
		getContentPane().setLayout(null);
		
		//MyKeyListener listener = new MyKeyListener();
		
		txtAccount = new MyTextField();
		txtAccount.setText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		txtAccount.setForeground(Color.LIGHT_GRAY);
		txtAccount.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtAccount.setColumns(10);
		txtAccount.setBounds(25, 40, 182, 36);
		//txtAccount.addKeyListener(listener);
		getContentPane().add(txtAccount);
		
		txtMail = new MyTextField();
		txtMail.setText("\u8BF7\u8F93\u5165\u90AE\u7BB1");
		txtMail.setForeground(Color.LIGHT_GRAY);
		txtMail.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtMail.setColumns(10);
		txtMail.setBackground(pnlColor);
		txtMail.setBounds(25, 86, 182, 36);
		//txtMail.addKeyListener(listener);
		getContentPane().add(txtMail);
		
		txtCode = new MyTextField();
		txtCode.setText("\u8BF7\u8F93\u5165\u90AE\u7BB1\u6536\u5230\u7684\u9A8C\u8BC1\u7801");
		txtCode.setForeground(Color.LIGHT_GRAY);
		txtCode.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtCode.setColumns(10);
		txtCode.setBackground(pnlColor);
		txtCode.setBounds(25, 167, 182, 36);
		//txtCode.addKeyListener(listener);
		getContentPane().add(txtCode);
		
		JButton btnSubmit = new JButton("\u63D0\u4EA4");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String code  = txtCode.getText();
				String newPsw = new String (txtNewPsw.getPassword());
				if (code.equals(verifyCode)){
					String regex = "^(\\w{6,20})$";
					if (newPsw.matches(regex)){
						user.setPssword(newPsw);
						IManagerDAO userDAO = DAOFactory.createManagerDAO();
						boolean bool = userDAO.updateUser(user);
						if (bool){
							JOptionPane.showMessageDialog(null, "密码重置成功");
							fgp.dispose();
						}
					}else{
						JOptionPane.showMessageDialog(null, "密码格式不正确,请重新输入");
					}
				}
			}
		});
		btnSubmit.setForeground(fontColor);
		btnSubmit.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnSubmit.setBounds(94, 274, 72, 36);
		getContentPane().add(btnSubmit);
		
		txtNewPsw = new MyPasswordField();
		txtNewPsw.setText("\u65B0\u5BC6\u7801(6-20\u4F4D\u6570\u5B57\u6216\u5B57\u6BCD)");
		txtNewPsw.setForeground(Color.LIGHT_GRAY);
		txtNewPsw.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtNewPsw.setColumns(10);
		txtNewPsw.setBackground(pnlColor);
		txtNewPsw.setBounds(25, 213, 182, 36);
		//txtNewPsw.addKeyListener(listener);
		getContentPane().add(txtNewPsw);
		
		JButton button = new JButton("\u53D1\u9001\u9A8C\u8BC1\u7535\u5B50\u90AE\u4EF6");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = txtAccount.getText();
				String mail = txtMail.getText();
				IManagerDAO userDAO = DAOFactory.createManagerDAO();
				Manager getUser = userDAO.findManagerByName(userName);
				if (getUser == null){
					JOptionPane.showMessageDialog(null, userName+":用户不存在");
				}else{
					user = getUser;
					System.out.println(mail+","+user.getMail());
					if (!mail.equals(user.getMail())){
						JOptionPane.showMessageDialog(null, "输入的邮箱不正确");
					}else{
						IMailService mailService = ServiceFactory.createMailService();
						try {
							verifyCode = mailService.sendVerifyCode(user);
							JOptionPane.showMessageDialog(null, "验证码已发送,请检查你的邮箱");
						} catch (UnknownHostException e1) {
							JOptionPane.showMessageDialog(null,"网络错误,请稍后再试");
						}
					}
				}
			}
		});
		button.setForeground(fontColor);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		button.setBackground(new Color(224, 255, 255));
		button.setBounds(35, 132, 131, 23);
		getContentPane().add(button);

		fgp = this;
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
			}else if (txt == txtMail){
				if (!mailTyped){
					mailTyped = true;
					turn = true;
				}
			}else if(txt  ==  txtCode){
				if (!codeTyped){
					codeTyped = true;
					turn = true;
				}
			}else if(txt == txtNewPsw && !newPswTyped){
				newPswTyped = true;
				turn = true;
			}
			if (turn){
				txt.setForeground(fontColor);
				txt.setText("");
			}
		}
		
	}*/
}
