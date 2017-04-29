package cn.com.musicplayerserver.exception;

public class UserNotFoundException extends RuntimeException {
			public UserNotFoundException(){
				super("用户名未找到");
			}
}
