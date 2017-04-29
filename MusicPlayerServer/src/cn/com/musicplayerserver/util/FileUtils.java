package cn.com.musicplayerserver.util;

import java.io.File;
import java.io.FileInputStream;

public class FileUtils {
	private final String dir = "C:/Program Files (x86)/apache-tomcat-7.0.67/webapps/Music";
	public FileInputStream getStreamAt(String musicURL){
		FileInputStream fis = null;
		String fileName = musicURL.substring(musicURL.lastIndexOf("/")+1);
		System.out.println(fileName);
		File file = new File(dir,fileName);
		if (file.exists() && file.isFile()){
		try{
				fis = new FileInputStream(file);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return fis;
	}
}
