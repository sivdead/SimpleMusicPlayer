package cn.com.musicplayerserver.service;

import java.util.List;

import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.UserExistException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.vo.ManageAddVO;


public interface IAuthorityControlService {
	/**
	 * ��֤�˺�����
	 * 
	 * @param userName �û���
	 * @param password ����
	 * @return ��֤�ɹ�����True,���򷵻�false
	 * @throws UserNotFoundException  �û�������
	 * @throws PasswordErrorException �������
	 * @throws RuntimeException ��������
	 */
	public Boolean verifyUser(String userName,String password) throws UserNotFoundException,PasswordErrorException,RuntimeException;
	/**
	 * ע���û�
	 * 
	 * @param Userע��ҳ���ȡ����Ϣ
	 * @returnע��ɹ�True,ʧ��false
	 * @throws UserExistException�û����Ѵ���
	 */
//	public Boolean regUser(User user) throws UserExistException;
	public Boolean addAdmin(ManageAddVO vo)throws UserExistException;
	public List<Manager> selectUser(ManageAddVO vo);
}
