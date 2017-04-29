package cn.com.musicplayerserver.vo;

public class ManageAddVO {
	private String userName;
	private String psw;
	private String rePsw;
	private int spId;
	private int userId;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSpId() {
		return spId;
	}
	public void setSpId(int spId) {
		this.spId = spId;
	}
	public String getRePsw() {
		return rePsw;
	}
	public void setRePsw(String rePsw) {
		this.rePsw = rePsw;
	}
	private String mail;
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
	 * @return the password
	 */
	public String getPsw() {
		return psw;
	}
	/**
	 * @param password the password to set
	 */
	public void setPsw(String psw) {
		this.psw = psw;
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
}
