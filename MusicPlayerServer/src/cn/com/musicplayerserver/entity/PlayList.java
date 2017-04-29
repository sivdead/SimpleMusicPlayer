package cn.com.musicplayerserver.entity;

public class PlayList {
	private Integer listId;
	private Integer listTypeId;
	private String listName;
	private String listDesc;
	private String imgPath;
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	/**
	 * @return the listId
	 */
	public Integer getListId() {
		return listId;
	}
	/**
	 * @param listId the listId to set
	 */
	public void setListId(Integer listId) {
		this.listId = listId;
	}
	/**
	 * @return the listTypeId
	 */
	public Integer getListTypeId() {
		return listTypeId;
	}
	/**
	 * @param listTypeId the listTypeId to set
	 */
	public void setListTypeId(Integer listTypeId) {
		this.listTypeId = listTypeId;
	}
	/**
	 * @return the listName
	 */
	public String getListName() {
		return listName;
	}
	/**
	 * @param listName the listName to set
	 */
	public void setListName(String listName) {
		this.listName = listName;
	}
	/**
	 * @return the listDesc
	 */
	public String getListDesc() {
		return listDesc;
	}
	/**
	 * @param listDesc the listDesc to set
	 */
	public void setListDesc(String listDesc) {
		this.listDesc = listDesc;
	}
}
