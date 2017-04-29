package cn.com.musicplayerserver.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.service.IAuthorityControlService;
import cn.com.musicplayerserver.service.ServiceFactory;
import cn.com.musicplayerserver.ui.adddata.AddAdmin;
import cn.com.musicplayerserver.ui.model.UserTableModel;
import cn.com.musicplayerserver.vo.ManageAddVO;
public class UserManage extends JPanel {
	private JTextField txtName;
	private JTable table;
	private static UserManage userManage= null;
	private JComboBox userType;
	private UserPopupMenu userPopupMenu = null;

	/**
	 * Create the panel.
	 */
	private  UserManage() {
		setBorder(new TitledBorder(null, "\u7528\u6237\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.setLocation(0,0);
		setSize(785,500);
		setLayout(null);
		
		JButton button = new JButton("\u6DFB\u52A0");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(MainUI.currUser != null && MainUI.currUser.getUserType() == 3){
					AddAdmin addAdmin = AddAdmin.getInstance();
					addAdmin.setVisible(true);
				}else
					JOptionPane.showMessageDialog(null, "错误：权限不足");
			}
		});
		button.setForeground(Color.DARK_GRAY);
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(294, 460, 75, 30);
		add(button);
		
		JButton button_2 = new JButton("\u5220\u9664");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserTableModel userModel = (UserTableModel)table.getModel();
				Manager manager = userModel.getManager(table.getSelectedRow());
				if (manager != null){
					IManagerDAO managerDAO = DAOFactory.createManagerDAO();
					managerDAO.deleteUser(manager);
				}
				List<Manager> userList = userModel.getManagerList();
				userList.remove(manager);
				userModel.setManagerList(userList);
				
			}
		});
		button_2.setForeground(Color.DARK_GRAY);
		button_2.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_2.setBounds(444, 460, 75, 30);
		add(button_2);
		
		txtName = new JTextField();
		txtName.setForeground(Color.DARK_GRAY);
		txtName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		txtName.setColumns(10);
		txtName.setBounds(140, 56, 253, 30);
		add(txtName);
		
		JButton button_3 = new JButton("\u67E5\u8BE2");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//获取页面信息
				String name = txtName.getText();
				String user = new String(userType.getActionCommand());
				int usertype = -1;
			    if(user.equals("普通用户")){
			    	usertype = 61;
			    }else{
			    	usertype = 62;
			    }
			    System.out.println(name+","+usertype);
			    ManageAddVO vo = new ManageAddVO();
			    vo.setSpId(usertype);
			    vo.setUserName(name);
			    //调用IAuthorityControlService
			    IAuthorityControlService service = ServiceFactory.createAuthorityControlService();
			    List<Manager> mana = service.selectUser(vo);
			    if(mana.isEmpty()){
			    	JOptionPane.showMessageDialog(null, "没有找到匹配的用户");
			    }
			    UserTableModel userName = new UserTableModel();
			    Manager ma = null;
			    System.out.println(mana.isEmpty());
			    List<Manager> mm = new ArrayList<Manager>(); 
			    for(Manager m: mana){
			    	ma = new Manager();
			    	ma.setUserName(m.getUserName());
			    	ma.setUserType(m.getUserType());
			    	ma.setMail(m.getMail());
			    	mm.add(ma);
			    }
			    userName.setManagerList(mm);
			    table.setModel(userName);
			}
		});
		button_3.setForeground(Color.DARK_GRAY);
		button_3.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button_3.setBounds(509, 55, 75, 30);
		add(button_3);
		
		JPanel panel = new JPanel();
		panel.setBounds(63, 101, 657, 335);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3){
					UserTableModel userModel = (UserTableModel)table.getModel();
					Manager manager = userModel.getManager(table.getSelectedRow());
					userPopupMenu = new UserPopupMenu(manager,table);
				}else if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1){
					
				}
			}
		});
		scrollPane.setViewportView(table);
		
		userType = new JComboBox();
		userType.addItem("管理员");
		userType.addItem("普通用户");
		userType.setBounds(416, 61, 83, 21);
		add(userType);
	}

	public static UserManage getInstance() {
		if (userManage == null){
			userManage = new UserManage();
		}
		userManage.repaint();
		return userManage;
	}
}
