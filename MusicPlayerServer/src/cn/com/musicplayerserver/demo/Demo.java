package cn.com.musicplayerserver.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		Date date = null;
		try {
			date = dateFormat.parse("19920202");
		} catch (ParseException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		System.out.println(date);
	}

}
