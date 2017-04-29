package cn.com.musicplayerserver.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cn.com.musicplayerserver.dao.IMusicDAO;
import cn.com.musicplayerserver.db.DBConnection;
import cn.com.musicplayerserver.entity.Music;

public class MusicDAOImpl implements IMusicDAO {

	DBConnection dbConn = null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public List<Music> getMusicByName(String musicName) {
		List<Music> musicList = null;
		// 创建list列表
		musicList = new ArrayList<Music>();
		try {
			// 1.创建dbconn
			dbConn = DBConnection.getInstance();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "select * from table_music where music_name like ?";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			pstmt.setString(1, "%"+musicName+"%");
			// 6.得到ResultSet
			rs = pstmt.executeQuery();
			// 7.处理结果
			Music music;
			while (rs != null && rs.next()) {
				// (1).创建music对象
				music = new Music();
				// (2).对music对象进行封装
				music.setMusicId(rs.getInt("music_id"));
				music.setSingerId(rs.getInt("singer_id"));
				music.setAlbumId(rs.getInt("album_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicLength((int) rs.getLong("music_length"));
				music.setMusicCount(rs.getInt("music_count"));
				music.setLyricPath(rs.getString("music_lyric"));
				music.setImgPath(rs.getString("music_img"));
				music.setMusicUrl(rs.getString("music_url"));
				// (3).将数据放入musicList中
				// 将封装好的music对象先进musicList中
				musicList.add(music);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public List<Music> getMusicBySinger(String singerName) {
		List<Music> musicList = null;
		// 创建list列表
		musicList = new ArrayList<Music>();
		try {
			// 1.创建dbconn
			dbConn =  DBConnection.getInstance();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "select * from table_music m inner join table_singer s on m.singer_id = s.singer_id where s.singer_name like ?";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			pstmt.setString(1, "%"+singerName+"%");
			// 6.得到ResultSet
			rs = pstmt.executeQuery();
			// 7.处理结果
			Music music;
			while (rs != null && rs.next()) {
				// (1).创建music对象
				music = new Music();
				// (2).对music对象进行封装
				music.setMusicId(rs.getInt("music_id"));
				music.setSingerId(rs.getInt("singer_id"));
				music.setAlbumId(rs.getInt("album_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicLength((int) rs.getLong("music_length"));
				music.setMusicCount(rs.getInt("music_count"));
				music.setLyricPath(rs.getString("music_lyric"));
				music.setImgPath(rs.getString("music_img"));
				music.setMusicUrl(rs.getString("music_url"));
				// (3).将数据放入musicList中
				// 将封装好的music对象先进musicList中
				musicList.add(music);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public List<Music> getMusicByAlbum(String albumName) {
		List<Music> musicList = null;
		// 创建list列表
		musicList = new ArrayList<Music>();
		try {
			// 1.创建dbconn
			dbConn = DBConnection.getInstance();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "select * from table_music m inner join table_album a on m.singer_id = a.singer_id where a.album_name like ?";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			pstmt.setString(1, "%"+albumName+"%");
			// 6.得到ResultSet
			rs = pstmt.executeQuery();
			// 7.处理结果
			Music music;
			while (rs != null && rs.next()) {
				// (1).创建music对象
				music = new Music();
				// (2).对music对象进行封装
				music.setMusicId(rs.getInt("music_id"));
				music.setSingerId(rs.getInt("singer_id"));
				music.setAlbumId(rs.getInt("album_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicLength((int) rs.getLong("music_length"));
				music.setMusicCount(rs.getInt("music_count"));
				music.setLyricPath(rs.getString("music_lyric"));
				music.setImgPath(rs.getString("music_img"));
				music.setMusicUrl(rs.getString("music_url"));
				// (3).将数据放入musicList中
				// 将封装好的music对象先进musicList中
				musicList.add(music);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public List<Music> getMusicByPlayList(String playListName) {
		List<Music> musicList = null;
		// 创建list列表
		musicList = new ArrayList<Music>();
		try {
			// 1.创建dbconn
			dbConn = DBConnection.getInstance();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "select * from table_music m inner join table_singer s on m.singer_id = s.singer_id inner join table_playlist p on s.sp_id = p.sp_id where p.playlist_name = ?";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			pstmt.setString(1, playListName);
			// 6.得到ResultSet
			rs = pstmt.executeQuery();
			// 7.处理结果
			Music music;
			while (rs != null && rs.next()) {
				// (1).创建music对象
				music = new Music();
				// (2).对music对象进行封装
				music.setMusicId(rs.getInt("music_id"));
				music.setSingerId(rs.getInt("singer_id"));
				music.setAlbumId(rs.getInt("album_id"));
				music.setMusicName(rs.getString("music_name"));
				music.setMusicLength((int) rs.getLong("music_length"));
				music.setMusicCount(rs.getInt("music_count"));
				music.setLyricPath(rs.getString("music_lyric"));
				music.setImgPath(rs.getString("music_img"));
				music.setMusicUrl(rs.getString("music_url"));
				// (3).将数据放入musicList中
				// 将封装好的music对象先进musicList中
				musicList.add(music);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 8.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public Boolean addMusic(Music music) {
		boolean bool = false;
		int count = -1;
		try {
			// 1.创建dbconn
			dbConn = new DBConnection();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "insert into table_music(music_id,singer_id,album_id,music_name,music_length,music_count,music_lyric,music_img,music_url) values (seq_musicPlayer.nextval,?,?,?,?,?,?,?,?)";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			// (2).对music对象进行封装
			pstmt.setInt(1, music.getSingerId());
			pstmt.setInt(2, music.getAlbumId());
			pstmt.setString(3, music.getMusicName());
			pstmt.setLong(4, 1);
			pstmt.setInt(5, 1);
			pstmt.setString(6, music.getLyricPath());
			pstmt.setString(7, music.getImgPath());
			pstmt.setString(8, music.getMusicUrl());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		if (count > 0) {
			bool = true;
		}
		return bool;
	}

	@Override
	public Boolean updateMusic(Music music) {
		boolean bool = false;
		int count = -1;
		try {
			// 1.创建dbconn
			dbConn = new DBConnection();
			// 2.得到连接
			conn = dbConn.getConnection();
			// 3.建立sql
			String sql = "update table_music m set m.music_id = ?,m.singer_id = ?,m.album_id = ?,m.music_name = ?,m.music_length = ?,m.music_count = ?,m.music_lyric = ?,m.music_img = ?,m.music_url = ? ";
			// 4.创建pstmt
			pstmt = conn.prepareStatement(sql);
			// 5.指定字段代替?
			// (1).创建music对象
			music = new Music();
			// (2).对music对象进行封装
			pstmt.setInt(1, music.getMusicId());
			pstmt.setInt(2, music.getSingerId());
			pstmt.setInt(3, music.getAlbumId());
			pstmt.setString(4, music.getMusicName());
			pstmt.setLong(5, music.getMusicLength());
			pstmt.setInt(6, music.getMusicCount());
			pstmt.setString(7, music.getLyricPath());			pstmt.setString(8, music.getImgPath());
			pstmt.setString(9, music.getMusicUrl());

			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 6.关闭资源
			dbConn.close(conn, pstmt, rs);
		}
		if (count > 0) {
			bool = true;
		}
		return bool;
	}

	@Override
	public List<Music> getMusicRankByHeat() {
		List<Music> musicList = null;
		dbConn = DBConnection.getInstance();
		// sql:SELECT
		try {
			conn = dbConn.getConnection();
			String sql = "SELECT a.* FROM table_music a" + "(SELCT * FROM table_music" + "Order By music_count) b"
					+ "WHERE a.music_id = b.music_id" + "AND rownum<=10";
			musicList = new ArrayList<Music>();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Music music;
			while (rs.next()) {
				music = new Music();
				music.setMusicName(rs.getString("music_name"));
				music.setSingerId(rs.getInt("singer_id"));
				musicList.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public List<Music> getMusicRankByDowload() {
		List<Music> musicList = null;
		dbConn = DBConnection.getInstance();
		// sql:SELECT
		try {
			conn = dbConn.getConnection();
			String sql = "SELECT a.* FROM table_music a INNER JOIN" + "(SELCT a.music_id FROM table_music a INNER JOIN download_record b ON a.music_id = b.music_id GROUP BY a.music_id" + "Order By count(b.dr_id)) b"
					+ "ON a.music_id=b.music_id WHERE rownum<=10";
			musicList = new ArrayList<Music>();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Music music;
			while (rs.next()) {
				music = new Music();
				music.setMusicName(rs.getString("music_name"));
				music.setSingerId(rs.getInt("singer_id"));
				musicList.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public List<Music> getMusicRankBySearch() {
		List<Music> musicList = null;
		dbConn = DBConnection.getInstance();
		// sql:SELECT
		try {
			conn = dbConn.getConnection();
			String sql = "SELECT a.* FROM table_music a INNER JOIN" + "(SELCT a.music_id FROM table_music a INNER JOIN search_record b ON a.music_id = b.music_id GROUP BY a.music_id" + "Order By count(b.dr_id)) b"
					+ "ON a.music_id=b.music_id WHERE rownum<=10";
			musicList = new ArrayList<Music>();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Music music;
			while (rs.next()) {
				music = new Music();
				music.setMusicName(rs.getString("music_name"));
				music.setSingerId(rs.getInt("singer_id"));
				musicList.add(music);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConn.close(conn, pstmt, rs);
		}
		return musicList;
	}

	@Override
	public Music getMusicById(int musicId) {
		Music music = null;
		DBConnection dbConn = DBConnection.getInstance();
		PreparedStatement stmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			conn = dbConn.getConnection();
			String sql = "SELECT * FROM table_music WHERE music_id = ?";
			stmt =conn.prepareStatement(sql);
			stmt.setInt(1, musicId);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()){
				music = new Music();
				music.setMusicId(musicId);
				music.setMusicName(rs.getString("music_name"));
				music.setMusicCount(rs.getInt("music_count"));
				music.setAlbumId(rs.getInt("album_id"));
				music.setMusicLength((int) rs.getLong("music_length"));
				music.setMusicUrl(rs.getString("music_url"));
				music.setSingerId(rs.getInt("singer_id"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dbConn.close(conn, stmt, rs);
		}
		return music;
	}

}   