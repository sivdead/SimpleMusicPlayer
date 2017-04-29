package cn.com.musicplayerserver.entity;

public class Album {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((singerId == null) ? 0 : singerId.hashCode());
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
		Album other = (Album) obj;
		if (singerId == null) {
			if (other.singerId != null)
				return false;
		} else if (!singerId.equals(other.singerId))
			return false;
		return true;
	}
	private Integer albumId;
	private String albumName;
	private Integer singerId;
	private String albumDesc;
	private String imgPath;
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
	 * @return the albumName
	 */
	public String getAlbumName() {
		return albumName;
	}
	/**
	 * @param albumName the albumName to set
	 */
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
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
	 * @return the albumDesc
	 */
	public String getAlbumDesc() {
		return albumDesc;
	}
	/**
	 * @param albumDesc the albumDesc to set
	 */
	public void setAlbumDesc(String albumDesc) {
		this.albumDesc = albumDesc;
	}
}
