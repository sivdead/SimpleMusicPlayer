package cn.com.musicplayerserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.musicplayerserver.dao.ISingerDAO;
import cn.com.musicplayerserver.db.DBConnection;
import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Singer;

public class SingerDAOImpl implements ISingerDAO {

	DBConnection dbConn = new DBConnection();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	@Override
	public boolean addSinger(Singer singer) {
		boolean bool = false;
		try{
			conn = dbConn.getConnection();
			String sql = "insert into table_singer(singer_id,singer_name,singer_intro,singer_sex,singer_nation,singer_birthday) values(seq_musicPlayer.nextval,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, singer.getSingerName());
			pstmt.setString(2, singer.getIntro());
			pstmt.setInt(3, singer.getSexId());
			pstmt.setInt(4, singer.getNationId());
			pstmt.setDate(5, singer.getBirthday());
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
	public boolean updateSinger(Singer singer) {
		boolean bool = false;
		try{
			conn = dbConn.getConnection();
			String sql = "update table_singer set sp_id=?,sys_sp_id=?,singer_name=?,singer_intro=?,singer_sex=?,singer_nation=?,singer_birthday=? where singer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, singer.getSpId());
			pstmt.setInt(2, singer.getSysSpId());
			pstmt.setString(3, singer.getSingerName());
			pstmt.setString(4, singer.getIntro());
			pstmt.setInt(5, singer.getSexId());
			pstmt.setInt(6, singer.getNationId());
			pstmt.setDate(7, singer.getBirthday());
			pstmt.setInt(8, singer.getSingerId());
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
	public boolean delSinger(Singer singer) {
		boolean bool = false;
		try{
			conn = dbConn.getConnection();
			String sql = "delete from table_singer where singer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, singer.getSingerId());
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
	public List<Singer> selectSinger(String singer) {
		List<Singer> sing = null;
		sing = new ArrayList<Singer>();
		Singer sin = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select * from table_singer where singer_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+singer+"%");
			rs = pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				sin = new Singer();
				sin.setNationId(rs.getInt("singer_nation"));
				sin.setSexId(rs.getInt("singer_sex"));
				sin.setSingerId(rs.getInt("singer_id"));
				sin.setSingerName(rs.getString("singer_name"));
				sing.add(sin);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return sing;
	}

	@Override
	public Singer selectSinger(int albumId) {
		Singer sing = null;
		sing = new Singer();
		try{
			conn = dbConn.getConnection();
			String sql = "select singer_name,singer_id from table_singer a inner join table_album b on a.singer_id=b.singer_id where album_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumId);
			rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				sing.setSingerId(rs.getInt("singer_id"));
				sing.setSingerName(rs.getString("singer_name"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return sing;
	}

	@Override
	public Singer getSingerBySingerId(Integer singerId) {
		Singer sing = null;
		sing = new Singer();
		try{
			conn = dbConn.getConnection();
			String sql = "select * from table_singer where singer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, singerId);
			rs = pstmt.executeQuery();
			if(rs!=null&&rs.next()){
				sing.setNationId(rs.getInt("singer_nation"));
				sing.setSingerId(rs.getInt("singer_id"));
				sing.setSpId(rs.getInt("sp_id"));
				sing.setSysSpId(rs.getInt("sys_sp_id"));
				sing.setSingerName(rs.getString("singer_name"));
				sing.setIntro(rs.getString("singer_intro"));
				sing.setSexId(rs.getInt("singer_sex"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs); 
		}
		
		return sing;
	}

	@Override
	public Singer getSingerByAlbum(String albumName) {
		Singer singer = null;
		try {
			// 1.创建dbconn
			dbConn = new DBConnection();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "select * from table_singer a inner join table_album b on a.singer_id = b.singer_id where b.album_name = ?";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			pstmt.setString(1, albumName);
			// 6.得到ResultSet
			rs = pstmt.executeQuery();
			// 7.处理结果
			if (rs != null && rs.next()) {
				// (1).创建singer对象
				singer = new Singer();
				// (2).对singer对象进行封装
				singer.setNationId(rs.getInt("singer_nation"));
				singer.setSexId(rs.getInt("singer_sex"));
				singer.setSingerId(rs.getInt("singer_id"));
				singer.setSingerName(rs.getString("singer_name"));
				singer.setIntro(rs.getString("singer_intro"));
				singer.setBirthday(rs.getDate("singer_birthday"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		return singer;
	}

		@Override
		public List<Singer> getSingerByName(String SingerName) {
			List<Singer> singerList = null;
			//创建list列表
			singerList = new ArrayList<Singer>();
			try {
				// 1.创建dbconn
				dbConn = new DBConnection();
				// 2.得到连接
				conn = dbConn.getConnection();
				// 3.建立sql
				String sql = "select * from table_singer where singer_name like ?";
				// 4.创建pstmt
				pstmt = conn.prepareStatement(sql);
				// 5.指定字段代替?
				pstmt.setString(1, "%"+SingerName+"%");
				// 6.得到ResultSet
				rs = pstmt.executeQuery();
				// 7.处理结果
				Singer singer;
				while (rs!=null && rs.next()){
					//(1).创建singer对象
					singer = new Singer();
					//(2).对singer对象进行封装
					singer.setNationId(rs.getInt("singer_nation"));
					singer.setSexId(rs.getInt("singer_sex"));
					singer.setSingerId(rs.getInt("singer_id"));
					singer.setSingerName(rs.getString("singer_name"));
					singer.setIntro(rs.getString("singer_intro"));
					singer.setBirthday(rs.getDate("singer_birthday"));
					//(3).将数据放入musicList中
					//将封装好的music对象先进musicList中
					singerList.add(singer);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 8.关闭资源
				dbConn.close(conn, pstmt, rs);
			}
			return singerList;
		}

		@Override
		public Singer getSingerByMusic(String keyword) {
			Singer singer = null;
			//创建list列表
			try {
				// 1.创建dbconn
				dbConn = new DBConnection();
				// 2.得到连接
				conn = dbConn.getConnection();
				// 3.建立sql
				String sql = "select * from table_singer a inner join table_music b on a.singer_id=b.singer_id where b.music_name like ?";
				// 4.创建pstmt
				pstmt = conn.prepareStatement(sql);
				// 5.指定字段代替?
				pstmt.setString(1, "%"+keyword+"%");
				// 6.得到ResultSet
				rs = pstmt.executeQuery();
				// 7.处理结果
				if (rs!=null && rs.next()){
					//(1).创建singer对象
					singer = new Singer();
					//(2).对singer对象进行封装
					singer.setNationId(rs.getInt("singer_nation"));
					singer.setSexId(rs.getInt("singer_sex"));
					singer.setSingerId(rs.getInt("singer_id"));
					singer.setSingerName(rs.getString("singer_name"));
					singer.setIntro(rs.getString("singer_intro"));
					singer.setBirthday(rs.getDate("singer_birthday"));
					//(3).将数据放入musicList中
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 8.关闭资源
				dbConn.close(conn, pstmt, rs);
			}
			return singer;
		}


}
