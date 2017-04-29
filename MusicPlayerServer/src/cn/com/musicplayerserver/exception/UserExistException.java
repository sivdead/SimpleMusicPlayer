package cn.com.musicplayerserver.exception;

public class UserExistException extends Exception {

	public UserExistException() {
		super("用户名已存在");
	}

}
