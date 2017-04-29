package cn.com.musicplayerserver.service.impl;

import java.util.List;

import javax.swing.JOptionPane;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.UserExistException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.service.IAuthorityControlService;
import cn.com.musicplayerserver.util.Tools;
import cn.com.musicplayerserver.vo.ManageAddVO;

public class AuthorityControlServiceImpl implements IAuthorityControlService {

	/**
	 * 验证账号密码
	 * 
	 * @param userName
	 *            用户名
	 * @param password
	 *            密码
	 * @return 验证成功返回True,否则返回false
	 * @throws UserNotFoundException
	 *             用户不存在
	 * @throws PasswordErrorException
	 *             密码错误
	 * @throws RuntimeException
	 *             其他错误
	 */
	@Override
	public Boolean verifyUser(String userName, String password)
			throws UserNotFoundException, PasswordErrorException, RuntimeException {
		boolean bool = false;
		// 对是否输入了参数的验证
		if (Tools.isEmpty(userName) || Tools.isEmpty(password)) {
			throw new RuntimeException("账号和密码不能为空");
		}
		// 创建IUserDAO对象
		IManagerDAO userDAO = DAOFactory.createManagerDAO();
		// 根据账号获取User对象
		Manager user = userDAO.findManagerByName(userName);
		if (user == null) {
			// 抛出账号不存在的异常
			throw new UserNotFoundException();
		} else {
			if (user.getUserType() == 1) {
				JOptionPane.showMessageDialog(null, "没有登陆权限");
				throw new RuntimeException("没有登陆权限");
			} else {
				// 验证密码是否正确
				if (!user.getPssword().equals(password)) {
					throw new PasswordErrorException();
				} else {
					bool = true;
				}
			}
		}
		return bool;
	}

	// @Override
	// public Boolean regUser(User user) throws UserExistException {
	// return null;
	// }

	@Override
	public Boolean addAdmin(ManageAddVO vo) throws UserExistException {
		boolean bool = false;
		String sttr = null;
		String strr = null;
		Manager manage = null;
		// 获取输入参数
		if (Tools.isEmpty(vo.getUserName()) || Tools.isEmpty(vo.getPsw()) || Tools.isEmpty(vo.getRePsw())
				|| Tools.isEmpty(vo.getMail())) {
			throw new RuntimeException("账户名，密码，邮箱不能为空");
		}
		// 账户名^[a-zA-Z]\w{5,14}$格式验证
		String str = "^[a-zA-Z]\\w{5,14}$";
		if (!Tools.verifyRegex(vo.getUserName(), str)) {
			JOptionPane.showMessageDialog(null, "账户名格式不符合");
			throw new RuntimeException("账户名格式不符合");
		} else {
			// 创建IManagerDAO
			IManagerDAO manageDAO = DAOFactory.createManagerDAO();
			Manager manager = manageDAO.findManagerByName(vo.getUserName());
			if (manager != null) {
				throw new UserExistException();
			} else {
				// 密码^[a-zA-Z0-9]{6,10}$格式验证
				sttr = "^[a-zA-Z0-9]{6,20}$";
				if (!Tools.verifyRegex(vo.getPsw(), sttr)) {
					JOptionPane.showMessageDialog(null, "密码格式不符合");
					throw new RuntimeException("密码格式不符合");
				}
				// 邮箱^\w+@{1}[a-zA-Z0-9]+(.com|.cn|.net|.org){1}$格式验证
				strr = "^\\w+@{1}[a-zA-Z0-9]+(.com|.cn|.net|.org){1}$";
				if (!Tools.verifyRegex(vo.getMail(), strr)) {
					JOptionPane.showMessageDialog(null, "邮箱格式不符合");
					throw new RuntimeException("邮箱格式不符合");
				}
				if (Tools.verifyRegex(vo.getPsw(), sttr) && Tools.verifyRegex(vo.getMail(), strr)) {
					bool = true;
					manage = new Manager();
					manage.setUserName(vo.getUserName());
					manage.setPssword(vo.getPsw());
					manage.setMail(vo.getMail());
					manage.setUserType(2);
					manageDAO.addManage(manage);
				}
			}
		}
		return bool;
	}

	@Override
	public List<Manager> selectUser(ManageAddVO vo) {
		IManagerDAO manager = DAOFactory.createManagerDAO();
		List<Manager> mana = manager.findMangerBystr(vo.getUserName(), vo.getSpId());
		return mana;
	}

}
