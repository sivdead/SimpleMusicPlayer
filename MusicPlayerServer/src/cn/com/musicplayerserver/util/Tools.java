package cn.com.musicplayerserver.util;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	public static boolean isEmpty(String str){
		return str==null||"".equals(str);
	}

	public static boolean isEmpty(int str) {
		return "".equals(str);
	}

	public static boolean isEmpty(Date str) {
		return str==null||"".equals(str);
	}
	public static boolean verifyRegex(String str,String reg){
		boolean bool = false;
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		bool = matcher.matches();
		return bool;
	}
}
