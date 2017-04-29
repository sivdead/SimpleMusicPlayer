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
	 * 更改密码
	 * 
	 * @param pswVO 从更改密码界面中得到的信息
	 * @return 更改成功返回True,否则返回false
	 * @throws PasswordErrorException密码错误抛出此异常
	 * @throws UserNotFoundException 用户未找到
	 */
	@Override
	public Boolean resetPassword(PswResetVO pswVO)
			throws PasswordErrorException, UserNotFoundException ,PasswordNotEqualsException{
		boolean bool = false;
		String str = null;
		//简单的验证
		if(Tools.isEmpty(pswVO.getUserName())||Tools.isEmpty(pswVO.getReNewPsw())||Tools.isEmpty(pswVO.getPassword())||Tools.isEmpty(pswVO.getNewPsw())){
			throw new RuntimeException("参数不能为空");
		}
		//创建IUserDAO
		IManagerDAO userDAO = DAOFactory.createManagerDAO();
		//获取用户名对象
		Manager user = userDAO.findManagerByName(pswVO.getUserName());
		//用户名不存在
		if(user==null){
			throw new UserNotFoundException();
		}else{
			//用户名与密码不对应
			if(!pswVO.getPassword().equals(user.getPssword())){
				throw new PasswordErrorException();
			}else{
				//新密码和再次输入新密码不同
				if(!pswVO.getNewPsw().equals(pswVO.getReNewPsw())){
					throw new PasswordNotEqualsException();
				}else{
					//新密码的格式不符合
					str = "^[a-zA-Z0-9]{6,20}$";
					if (!Tools.verifyRegex(pswVO.getNewPsw(), str)) {
						JOptionPane.showMessageDialog(null, "密码格式不符合");
						throw new RuntimeException("密码格式不符合");
					}else{
						//新密码设置成功
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
	 * 忘记密码后找回密码
	 * @param fpswVO 忘记密码界面的信息
	 * @return 成功返回true,否则返回false;
	 * @throws MailErrorException	邮箱验证错误
	 * @throws UserNotFoundException 用户未找到
	 */
	@Override
	public Boolean regainPassword(ForgetPswVO fpswVO) throws MailErrorException, UserNotFoundException {
		// TODO 自动生成的方法存根
		return null;
	}

}
