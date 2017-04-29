package cn.com.musicplayerserver.service;

import java.util.List;

import cn.com.musicplayerserver.entity.Manager;
import cn.com.musicplayerserver.exception.PasswordErrorException;
import cn.com.musicplayerserver.exception.UserExistException;
import cn.com.musicplayerserver.exception.UserNotFoundException;
import cn.com.musicplayerserver.vo.ManageAddVO;


public interface IAuthorityControlService {
	/**
	 * 验证账号密码
	 * 
	 * @param userName 用户名
	 * @param password 密码
	 * @return 验证成功返回True,否则返回false
	 * @throws UserNotFoundException  用户不存在
	 * @throws PasswordErrorException 密码错误
	 * @throws RuntimeException 其他错误
	 */
	public Boolean verifyUser(String userName,String password) throws UserNotFoundException,PasswordErrorException,RuntimeException;
	/**
	 * 注册用户
	 * 
	 * @param User注册页面获取的信息
	 * @return注册成功True,失败false
	 * @throws UserExistException用户名已存在
	 */
//	public Boolean regUser(User user) throws UserExistException;
	public Boolean addAdmin(ManageAddVO vo)throws UserExistException;
	public List<Manager> selectUser(ManageAddVO vo);
}
