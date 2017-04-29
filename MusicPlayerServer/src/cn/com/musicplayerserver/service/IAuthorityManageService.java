package cn.com.musicplayerserver.service;

import cn.com.musicplayerserver.exception.MailErrorException;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.PasswordNotEqualsException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.vo.ForgetPswVO;
import cn.com.musicplayerserver.vo.PswResetVO;


public interface IAuthorityManageService {
	/**
	 * 更改密码
	 * 
	 * @param pswVO 从更改密码界面中得到的信息
	 * @return 更改成功返回True,否则返回false
	 * @throws PasswordErrorException密码错误抛出此异常
	 * @throws UserNotFoundException 用户未找到
	 * @throws PasswordNotEqualsException 
	 */
	public Boolean resetPassword(PswResetVO pswVO) throws PasswordErrorException,UserNotFoundException, PasswordNotEqualsException;
	/**
	 * 忘记密码后找回密码
	 * @param fpswVO 忘记密码界面的信息
	 * @return 成功返回true,否则返回false;
	 * @throws MailErrorException	邮箱验证错误
	 * @throws UserNotFoundException 用户未找到
	 */
	public Boolean regainPassword(ForgetPswVO fpswVO) throws MailErrorException,UserNotFoundException;
}
