package cn.com.musicplayerserver.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {
	private static String url;
	private static String userName;
	private static String password;
	private static DBConnection dbconn = null;
	public DBConnection(){
		
	}
	public static DBConnection getInstance(){
		if (dbconn == null){
			dbconn = new DBConnection();
		}
		return dbconn;
	}
	static{
		try{
			Properties prop = new Properties();
			InputStream in = DBConnection.class.getClassLoader().getResourceAsStream("dm.properties");
			prop.load(in);
			String driverName = prop.getProperty("driverName");
			url = prop.getProperty("url");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");
			Class.forName(driverName);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public Connection getConnection() throws Exception{
		Connection conn = null;
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}
	
	public void close(Connection conn,Statement stmt,ResultSet rs){
		try{
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (conn != null) conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
