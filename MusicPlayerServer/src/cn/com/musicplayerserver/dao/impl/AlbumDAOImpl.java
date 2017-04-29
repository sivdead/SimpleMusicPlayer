package cn.com.musicplayerserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.musicplayerserver.dao.IAlbumDAO;
import cn.com.musicplayerserver.db.DBConnection;
import cn.com.musicplayerserver.entity.Album;
import cn.com.musicplayerserver.entity.Singer;

public class AlbumDAOImpl implements IAlbumDAO{

	@Override
	public boolean addAlbum(Album album) {
		boolean bool = false;
		int count = -1;
		//��ʼ������
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//����dbConn
			dbConn = new DBConnection();
			//��ȡ����
			conn = dbConn.getConnection();
			//����sql
			String sql = "insert into table_album values (seq_musicPlayer.nextval,?,?,?,?)";
			//����pstmt
			pstmt = conn.prepareStatement(sql);
			//��album�����װ
			pstmt.setInt(1,album.getSingerId());
			pstmt.setString(2,album.getAlbumName());
			pstmt.setString(3,album.getAlbumDesc());
			pstmt.setString(4,album.getImgPath());
			//ִ��
			count = pstmt.executeUpdate();
			if(count==1){
				bool = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//�ر���Դ
			dbConn.close(conn, pstmt, rs);
		}
		return bool;
	}

	@Override
	public boolean delAlbum(Album album) {
		boolean bool = false;
		int count = -1;
		//��ʼ������
		DBConnection dbConn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//����dbConn
			dbConn = new DBConnection();
			//��ȡ����
			conn = dbConn.getConnection();
			//����sql
			String sql = "delete from table_album where album_id=?";
			//����pstmt
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, album.getAlbumId());
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

	@Override
	public boolean updateAlbum(Album album) {
		boolean bool = false;
		int count = -1;
		//��ʼ������
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
		//����dbConn
			dbConn = new DBConnection();
		//��ȡ����
			conn = dbConn.getConnection();
		//����sql
			String sql = "update table_album set singer_id=?,album_name=?,album_desc=?,album_img=? where album_id="+ album.getAlbumId();
		//����pstmt
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,album.getSingerId());
			pstmt.setString(2,album.getAlbumName());
			pstmt.setString(3,album.getAlbumDesc());
			pstmt.setString(4,album.getImgPath());
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

	@Override
	public List<Album> selectAlbum(String alb) {
		List<Album> album = null;
		album = new ArrayList<Album>();
		Album sin = null;
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select album_name,album_id from table_album where album_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+alb+"%");
			rs = pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				sin = new Album();
				sin.setAlbumId(rs.getInt("album_id"));
				sin.setAlbumName(rs.getString("album_name"));
				album.add(sin);
				System.out.println(sin.getAlbumName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return album;
	}

	@Override
	public List<Album> selectAlbum(String album, int singerid) {
		List<Album> albu = null;
		albu = new ArrayList<Album>();
		Album sin = null;
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select album_name,album_id from table_album where album_name like ? and singer_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+album+"%");
			pstmt.setInt(2, singerid);
			rs = pstmt.executeQuery();
			while(rs!=null&&rs.next()){
				sin = new Album();
				sin.setAlbumId(rs.getInt("album_id"));
				sin.setAlbumName(rs.getString("album_name"));
				albu.add(sin);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		return albu;
	}

	@Override
	public Album getAlbumByAlbumId(Integer albumId) {
		DBConnection dbConn = new DBConnection();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Album album = null;
		try{
			conn = dbConn.getConnection();
			String sql = "select * from table_album where album_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, albumId);
			rs = pstmt.executeQuery();
			if (rs!=null&&rs.next()){
				album = new Album();
				album.setAlbumName(rs.getString("album_Name"));
				album.setAlbumId(rs.getInt("album_id"));
				album.setSingerId(rs.getInt("singer_Id"));
				album.setAlbumDesc(rs.getString("album_Desc"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, pstmt, rs);
		}
		
		return album;
	}

	@Override
	public List<Album> getAlbumByName(String albumName) {
		List<Album> albumList = null;
		DBConnection dbconn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1.����dbconn
			dbconn = new DBConnection();
			// 2.�õ�����
			conn = dbconn.getConnection();
			// 3.����sql
			String sql = "select * from table_album where album_name like ?";
			// 4.����pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.ָ���ֶδ���?
			pstmt.setString(1, "%"+albumName+"%");
			// 6.�õ�ResultSet
			rs = pstmt.executeQuery();
			// 7.������
			Album album;
			// ����list�б�
			albumList = new ArrayList<Album>();
			while (rs != null && rs.next()) {
				// (1).����album����
				album = new Album();
				// (2).��album������з�װ
				album.setAlbumId(rs.getInt("album_id"));
				album.setSingerId(rs.getInt("singer_id"));
				album.setAlbumName(rs.getString("album_name"));
				album.setAlbumDesc(rs.getString("album_desc"));
				System.out.println(album.getAlbumName());
				album.setImgPath(rs.getString("album_img"));
				// (3).�����ݷ���albumList��
				// ����װ�õ�album�����Ƚ�deptList��
				albumList.add(album);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.�ر���Դ
			dbconn.close(conn, pstmt, rs);
		}
		return albumList;
	}

	@Override
	public List<Album> getAlbumBySinger(String singerName) {
		List<Album> album = new ArrayList<Album>();
		DBConnection dbconn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Album al = null;
		try {
			// 1.����dbconn
			dbconn = new DBConnection();
			// 2.�õ�����
			conn = dbconn.getConnection();
			// 3.����sql
			String sql = "select * from table_album inner join table_singer on table_album.singer_id = table_singer.singer_id where table_singer.singer_name like ?";
			// 4.����pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.ָ���ֶδ���?
			pstmt.setString(1, "%"+singerName+"%");
			// 6.�õ�ResultSet
			rs = pstmt.executeQuery();
			// 7.������
			while (rs != null && rs.next()) {
				// (1).����album����
				al = new Album();
				// (2).��album������з�װ
				al.setAlbumId(rs.getInt("album_id"));
				al.setSingerId(rs.getInt("singer_id"));
				al.setAlbumName(rs.getString("album_name"));
				al.setAlbumDesc(rs.getString("album_desc"));
				album.add(al);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.�ر���Դ
			dbconn.close(conn, pstmt, rs);
		}
		return album;
	}

	@Override
	public List<Album> getAlbumByMusic(String MusicName) {
		List<Album> al = new ArrayList<Album>();
		DBConnection dbconn = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Album album = null;
		try {
			// 1.����dbconn
			dbconn = new DBConnection();
			// 2.�õ�����
			conn = dbconn.getConnection();
			// 3.����sql
			String sql = "select * from table_album inner join table_music on table_album.album_id = table_music.album_id where table_music.music_name like ?";
			// 4.����pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.ָ���ֶδ���?
			pstmt.setString(1, "%"+MusicName+"%");
			// 6.�õ�ResultSet
			rs = pstmt.executeQuery();
			// 7.������
			while (rs != null && rs.next()) {
				// (1).����album����
				album = new Album();
				// (2).��album������з�װ
				album.setAlbumId(rs.getInt("album_id"));
				album.setSingerId(rs.getInt("singer_id"));
				album.setAlbumName(rs.getString("album_name"));
				album.setAlbumDesc(rs.getString("album_desc"));
				album.setImgPath(rs.getString("album_img"));
				al.add(album);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.�ر���Դ
			dbconn.close(conn, pstmt, rs);
		}
		return al;
	}


}
