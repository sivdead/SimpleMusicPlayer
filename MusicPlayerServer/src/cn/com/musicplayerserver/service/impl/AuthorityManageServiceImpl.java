package cn.com.musicplayerserver.service.impl;

import javax.swing.JOptionPane;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.exception.MailErrorException;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.PasswordNotEqualsException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.service.IAuthorityManageService;
import cn.com.musicplayerserver.util.Tools;
import cn.com.musicplayerserver.vo.ForgetPswVO;
import cn.com.musicplayerserver.vo.PswResetVO;

public class AuthorityManageServiceImpl implements IAuthorityManageService {

	/**
	 * ��������
	 * 
	 * @param pswVO �Ӹ�����������еõ�����Ϣ
	 * @return ���ĳɹ�����True,���򷵻�false
	 * @throws PasswordErrorException��������׳����쳣
	 * @throws UserNotFoundException �û�δ�ҵ�
	 */
	@Override
	public Boolean resetPassword(PswResetVO pswVO)
			throws PasswordErrorException, UserNotFoundException ,PasswordNotEqualsException{
		boolean bool = false;
		String str = null;
		//�򵥵���֤
		if(Tools.isEmpty(pswVO.getUserName())||Tools.isEmpty(pswVO.getReNewPsw())||Tools.isEmpty(pswVO.getPassword())||Tools.isEmpty(pswVO.getNewPsw())){
			throw new RuntimeException("��������Ϊ��");
		}
		//����IUserDAO
		IManagerDAO userDAO = DAOFactory.createManagerDAO();
		//��ȡ�û�������
		Manager user = userDAO.findManagerByName(pswVO.getUserName());
		//�û���������
		if(user==null){
			throw new UserNotFoundException();
		}else{
			//�û��������벻��Ӧ
			if(!pswVO.getPassword().equals(user.getPssword())){
				throw new PasswordErrorException();
			}else{
				//��������ٴ����������벻ͬ
				if(!pswVO.getNewPsw().equals(pswVO.getReNewPsw())){
					throw new PasswordNotEqualsException();
				}else{
					//������ĸ�ʽ������
					str = "^[a-zA-Z0-9]{6,20}$";
					if (!Tools.verifyRegex(pswVO.getNewPsw(), str)) {
						JOptionPane.showMessageDialog(null, "�����ʽ������");
						throw new RuntimeException("�����ʽ������");
					}else{
						//���������óɹ�
						bool = true;
						IManagerDAO usr = DAOFactory.createManagerDAO();
						Manager ur = usr.findManagerByName(pswVO.getUserName());
						ur.setPssword(pswVO.getNewPsw());
						usr.updateUser(ur);
					}
				}
			}
		}
		return bool;
	}

	/**
	 * ����������һ�����
	 * @param fpswVO ��������������Ϣ
	 * @return �ɹ�����true,���򷵻�false;
	 * @throws MailErrorException	������֤����
	 * @throws UserNotFoundException �û�δ�ҵ�
	 */
	@Override
	public Boolean regainPassword(ForgetPswVO fpswVO) throws MailErrorException, UserNotFoundException {
		// TODO �Զ����ɵķ������
		return null;
	}

}
