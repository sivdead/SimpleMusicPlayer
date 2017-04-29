package cn.com.musicplayerserver.service;

import cn.com.musicplayerserver.exception.MailErrorException;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.PasswordNotEqualsException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.vo.ForgetPswVO;
import cn.com.musicplayerserver.vo.PswResetVO;


public interface IAuthorityManageService {
	/**
	 * ��������
	 * 
	 * @param pswVO �Ӹ�����������еõ�����Ϣ
	 * @return ���ĳɹ�����True,���򷵻�false
	 * @throws PasswordErrorException��������׳����쳣
	 * @throws UserNotFoundException �û�δ�ҵ�
	 * @throws PasswordNotEqualsException 
	 */
	public Boolean resetPassword(PswResetVO pswVO) throws PasswordErrorException,UserNotFoundException, PasswordNotEqualsException;
	/**
	 * ����������һ�����
	 * @param fpswVO ��������������Ϣ
	 * @return �ɹ�����true,���򷵻�false;
	 * @throws MailErrorException	������֤����
	 * @throws UserNotFoundException �û�δ�ҵ�
	 */
	public Boolean regainPassword(ForgetPswVO fpswVO) throws MailErrorException,UserNotFoundException;
}
