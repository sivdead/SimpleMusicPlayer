package cn.com.musicplayerserver.entity;

import java.awt.Image;

public class Music {
	private Integer musicId;
	private Integer singerId;
	private Integer albumId;
	private String musicName;
	private Integer musicLength;
	private String musicUrl;
	private Integer musicTypeId;
	private Integer musicCount;
	private String imgPath;
	private String lyricPath;
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getLyricPath() {
		return lyricPath;
	}
	public void setLyricPath(String lyricPath) {
		this.lyricPath = lyricPath;
	}
	/**
	 * @return the musicId
	 */
	public Integer getMusicId() {
		return musicId;
	}
	/**
	 * @param musicId the musicId to set
	 */
	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}
	/**
	 * @return the singerId
	 */
	public Integer getSingerId() {
		return singerId;
	}
	/**
	 * @param singerId the singerId to set
	 */
	public void setSingerId(Integer singerId) {
		this.singerId = singerId;
	}
	/**
	 * @return the albumId
	 */
	public Integer getAlbumId() {
		return albumId;
	}
	/**
	 * @param albumId the albumId to set
	 */
	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}
	/**
	 * @return the musicName
	 */
	public String getMusicName() {
		return musicName;
	}
	/**
	 * @param musicName the musicName to set
	 */
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	/**
	 * @return the musicLength
	 */
	
	/**
	 * @return the musicUrl
	 */
	public String getMusicUrl() {
		return musicUrl;
	}
	/**
	 * @param musicUrl the musicUrl to set
	 */
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public Integer getMusicCount() {
		return musicCount;
	}
	public void setMusicCount(Integer musicCount) {
		this.musicCount = musicCount;
	}
	public Integer getMusicTypeId() {
		return musicTypeId;
	}
	public void setMusicTypeId(Integer musicTypeId) {
		this.musicTypeId = musicTypeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
		result = prime * result + ((musicId == null) ? 0 : musicId.hashCode());
		result = prime * result + ((musicName == null) ? 0 : musicName.hashCode());
		result = prime * result + ((singerId == null) ? 0 : singerId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Music other = (Music) obj;
		if (albumId == null) {
			if (other.albumId != null)
				return false;
		} else if (!albumId.equals(other.albumId))
			return false;
		if (musicId == null) {
			if (other.musicId != null)
				return false;
		} else if (!musicId.equals(other.musicId))
			return false;
		if (musicName == null) {
			if (other.musicName != null)
				return false;
		} else if (!musicName.equals(other.musicName))
			return false;
		if (singerId == null) {
			if (other.singerId != null)
				return false;
		} else if (!singerId.equals(other.singerId))
			return false;
		return true;
	}
	public Integer getMusicLength() {
		return musicLength;
	}
	public void setMusicLength(Integer l) {
		this.musicLength = l;
	}
}
