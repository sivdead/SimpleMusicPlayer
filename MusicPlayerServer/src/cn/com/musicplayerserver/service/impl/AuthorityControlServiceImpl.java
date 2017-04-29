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
	 * ��֤�˺�����
	 * 
	 * @param userName
	 *            �û���
	 * @param password
	 *            ����
	 * @return ��֤�ɹ�����True,���򷵻�false
	 * @throws UserNotFoundException
	 *             �û�������
	 * @throws PasswordErrorException
	 *             �������
	 * @throws RuntimeException
	 *             ��������
	 */
	@Override
	public Boolean verifyUser(String userName, String password)
			throws UserNotFoundException, PasswordErrorException, RuntimeException {
		boolean bool = false;
		// ���Ƿ������˲�������֤
		if (Tools.isEmpty(userName) || Tools.isEmpty(password)) {
			throw new RuntimeException("�˺ź����벻��Ϊ��");
		}
		// ����IUserDAO����
		IManagerDAO userDAO = DAOFactory.createManagerDAO();
		// �����˺Ż�ȡUser����
		Manager user = userDAO.findManagerByName(userName);
		if (user == null) {
			// �׳��˺Ų����ڵ��쳣
			throw new UserNotFoundException();
		} else {
			if (user.getUserType() == 1) {
				JOptionPane.showMessageDialog(null, "û�е�½Ȩ��");
				throw new RuntimeException("û�е�½Ȩ��");
			} else {
				// ��֤�����Ƿ���ȷ
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
		// ��ȡ�������
		if (Tools.isEmpty(vo.getUserName()) || Tools.isEmpty(vo.getPsw()) || Tools.isEmpty(vo.getRePsw())
				|| Tools.isEmpty(vo.getMail())) {
			throw new RuntimeException("�˻��������룬���䲻��Ϊ��");
		}
		// �˻���^[a-zA-Z]\w{5,14}$��ʽ��֤
		String str = "^[a-zA-Z]\\w{5,14}$";
		if (!Tools.verifyRegex(vo.getUserName(), str)) {
			JOptionPane.showMessageDialog(null, "�˻�����ʽ������");
			throw new RuntimeException("�˻�����ʽ������");
		} else {
			// ����IManagerDAO
			IManagerDAO manageDAO = DAOFactory.createManagerDAO();
			Manager manager = manageDAO.findManagerByName(vo.getUserName());
			if (manager != null) {
				throw new UserExistException();
			} else {
				// ����^[a-zA-Z0-9]{6,10}$��ʽ��֤
				sttr = "^[a-zA-Z0-9]{6,20}$";
				if (!Tools.verifyRegex(vo.getPsw(), sttr)) {
					JOptionPane.showMessageDialog(null, "�����ʽ������");
					throw new RuntimeException("�����ʽ������");
				}
				// ����^\w+@{1}[a-zA-Z0-9]+(.com|.cn|.net|.org){1}$��ʽ��֤
				strr = "^\\w+@{1}[a-zA-Z0-9]+(.com|.cn|.net|.org){1}$";
				if (!Tools.verifyRegex(vo.getMail(), strr)) {
					JOptionPane.showMessageDialog(null, "�����ʽ������");
					throw new RuntimeException("�����ʽ������");
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
