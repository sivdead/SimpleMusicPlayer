package cn.com.musicplayerserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.musicplayerserver.dao.IManagerDAO;
import cn.com.musicplayerserver.db.DBConnection;
import cn.com.musicplayerserver.entity.Manager;

public class ManagerDAOImpl implements IManagerDAO{

	DBConnection dbConn = new DBConnection();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public Manager findManagerByName(String name) {
		Manager manager = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select * from table_user where user_userName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
		    rs = pstmt.executeQuery();
		    if(rs!=null&&rs.next()){
		    	manager = new Manager();
		    	manager.setUserId(rs.getInt("user_id"));
		    	manager.setUserName(rs.getString("user_userName"));
		    	manager.setPssword(rs.getString("user_password"));
		    	manager.setMail(rs.getString("user_mail"));
		    	manager.setUserType(rs.getInt("sp_id"));
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return manager;
	}

	@Override
	public Boolean updateUser(Manager manager) {
		boolean bool = false;
		try{
			conn = dbConn.getConnection();
			String sql = "update table_user set user_password=? where user_userName=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, manager.getPssword());
			pstmt.setString(2, manager.getUserName());
			int count = pstmt.executeUpdate();
			if(count==1){
				bool = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return bool;
	}

	@Override
	public Boolean addManage(Manager manage) {
		boolean bool = false;
		int count = -1;
		try{
			conn = dbConn.getConnection();
			String sql = "insert into table_user(user_id,sp_id,user_userName,user_password,user_mail) values(seq_musicPlayer.nextval,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, manage.getUserType());
			pstmt.setString(2, manage.getUserName());
			pstmt.setString(3, manage.getPssword());
			pstmt.setString(4, manage.getMail());
			count = pstmt.executeUpdate();
			if(count==1){
				bool = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, null);
		}
		return bool;
	}

	@Override
	public List<Manager> findMangerBystr(String nameStr,int typeId) {
		List<Manager> managerList = null;
		managerList = new ArrayList<Manager>();
		Manager manage = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select * from table_user where user_userName like ? and sp_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+nameStr+"%");
			pstmt.setInt(2, typeId);
			rs = pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				manage = new Manager();
				manage.setUserName(rs.getString("user_userName"));
				manage.setUserId(rs.getInt("user_id"));
		    	manage.setPssword(rs.getString("user_password"));
		    	manage.setMail(rs.getString("user_mail"));
		    	manage.setUserType(rs.getInt("sp_id"));
		    	managerList.add(manage);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return managerList;
	}

	@Override
	public Boolean deleteUser(Manager manager) {
		Boolean bool = false;
		int count = -1;
		try{
			conn = dbConn.getConnection();
			String sql = "delete from table_user where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,manager.getUserId());
		    count = pstmt.executeUpdate();
		    if(count==1){
		    	bool = true;
		    }
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return bool;
	}

}
