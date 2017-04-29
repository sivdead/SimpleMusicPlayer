package cn.com.musicplayerserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.musicplayerserver.dao.ISystemParamDAO;
import cn.com.musicplayerserver.db.DBConnection;
import cn.com.musicplayerserver.entity.SystemParam;

public class SystemParamDAOImpl implements ISystemParamDAO {

	DBConnection dbConn = new DBConnection();;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Override
	public List<SystemParam> selectSystemParam(String str) {
		List<SystemParam> sys = null;
		sys = new ArrayList<SystemParam>();
		SystemParam sysPa = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select sp_name from system_param where sp_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+str+"%");
			rs = pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				sysPa = new SystemParam();
				sysPa.setId(rs.getInt("sp_id"));
				sysPa.setValue(rs.getString("sp_value"));
				sysPa.setName(rs.getString("sp_name"));
				sys.add(sysPa);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return sys;
	}
	@Override
	public SystemParam getSystemParamById(int id) {
		SystemParam sysParam = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select * from system_param where sp_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				sysParam = new SystemParam();
				sysParam.setId(rs.getInt("sp_id"));
				sysParam.setName(rs.getString("sp_name"));
				sysParam.setValue(rs.getString("sp_value"));
				System.out.println(sysParam.getId()+","+sysParam.getName()+","+sysParam.getValue());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return sysParam;
	}
	@Override
	public SystemParam getSystemParamByNameAndValue(String Name, String Value) {
		SystemParam sysParam = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select * from system_param where sp_name=? AND sp_value=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Name);
			pstmt.setString(2, Value);
			rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				sysParam = new SystemParam();
				sysParam.setId(rs.getInt("sp_id"));
				sysParam.setName(rs.getString("sp_name"));
				sysParam.setValue(rs.getString("sp_value"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return sysParam;
	}


}
