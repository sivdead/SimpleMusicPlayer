package cn.com.musicplayerserver.service.impl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import cn.com.musicplayerserver.dao.DAOFactory;
import cn.com.musicplayerserver.dao.IMusicDAO;
import cn.com.musicplayerserver.entity.Music;
import cn.com.musicplayerserver.util.FileUtils;

public class MessageService extends Thread{
	public static void main (String[] args){
		new MessageService().start();
	}
	public void run(){
		ServerSocket server = null;
		try{
			server = new ServerSocket(1234);
			System.err.println("Server is running.");
			while (true){
				Socket socket = server.accept();
				System.err.println(socket.getInetAddress()+":"+socket.getPort()+"is connecting.");
				InputStream in = socket.getInputStream();
				DataInputStream dis = null;
				FileInputStream fis = null;
				DataOutputStream dos = null;
				OutputStream out = socket.getOutputStream();
				byte[] data;
				int count ;
				try{
					dis = new DataInputStream(in);
					//String msg = "download";
					String msg = dis.readUTF();
					System.err.println("message:"+msg);
					String[] s = msg.split(":");
//					try{
//						dis.close();
//					}catch(Exception e){
//						e.printStackTrace();
//					}
					switch(s[0]){
					case "download":
						dos = new DataOutputStream(out);
						//根据s[1]获取musicId来获取音乐对象
						IMusicDAO musicDAO = DAOFactory.createMusicDAO();
						Music music = musicDAO.getMusicById(Integer.parseInt(s[1]));
						FileUtils fileUtil = new FileUtils();
						fis = fileUtil.getStreamAt(music.getMusicUrl());
						data = new byte[1024];
						count = -1;
						while ((count = fis.read(data)) != -1){
							out.write(data,0,count);
						}
						System.err.println("download complete!");
						break;
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
						if (dis != null && in != null){
							dis.close();
							in.close();
						}
						if (dos != null && out != null){
							dos.flush();
							dos.close();
							out.flush();
							out.close();
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
