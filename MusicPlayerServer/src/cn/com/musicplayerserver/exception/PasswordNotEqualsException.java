package cn.com.musicplayerserver.exception;

public class PasswordNotEqualsException extends Exception {
	public PasswordNotEqualsException(){
		super("密码格式不符合");
	}
}
