package cn.com.musicplayerserver.vo;

public class ForgetPswVO {
	private String mail;
	private String code;
	private String newPsw;
	private String userName;
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
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the newPsw
	 */
	public String getNewPsw() {
		return newPsw;
	}
	/**
	 * @param newPsw the newPsw to set
	 */
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
}
