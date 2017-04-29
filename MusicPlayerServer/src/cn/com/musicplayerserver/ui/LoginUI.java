package cn.com.musicplayerserver.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;





import cn.com.musicplayerserver.ui.login.LoginPanel;
import cn.com.musicplayerserver.ui.login.PswResetPanel;

import java.awt.Color;



import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;


public class LoginUI extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private LoginPanel pnlLog;
	private PswResetPanel pnlReset;
	public PswResetPanel getPnlReset() {
		return pnlReset;
	}

	public void setPnlReset(PswResetPanel pnlReset) {
		this.pnlReset = pnlReset;
	}

	private static LoginUI  logUI = null;


	public LoginPanel getPnlLog() {
		return pnlLog;
	}

	public void setPnlLog(LoginPanel pnlLog) {
		this.pnlLog = pnlLog;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI frame = new LoginUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	/**
	 * Create the frame.
	 */
	private LoginUI() {
		setResizable(false);
		setBounds(100, 100, 300, 450);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 294, 411);
		contentPane.add(panel);
		panel.setLayout(null);
		
		pnlLog = new LoginPanel(this);
		panel.add(pnlLog);
		
	}

	public static LoginUI getInstance() {
		if (logUI == null){
			logUI = new LoginUI();
		}else{
			logUI.dispose();
			logUI = null;
			logUI = new LoginUI();
		}
		return logUI;
	}
}
