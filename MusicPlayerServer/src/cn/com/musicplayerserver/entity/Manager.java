package cn.com.musicplayerserver.entity;

public class Manager {
	private Integer userId;
	private String userName;
	private String pssword;
	private String mail;
	private Integer userType;
	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the pssword
	 */
	public String getPssword() {
		return pssword;
	}
	/**
	 * @param pssword the pssword to set
	 */
	public void setPssword(String pssword) {
		this.pssword = pssword;
	}
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
}
