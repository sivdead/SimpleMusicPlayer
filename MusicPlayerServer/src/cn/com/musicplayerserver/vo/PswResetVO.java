package cn.com.musicplayerserver.vo;

public class PswResetVO {
	private String password;
	private String userName;
	private String reNewPsw;
	private String newPsw;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getReNewPsw() {
		return reNewPsw;
	}
	public void setReNewPsw(String reNewPsw) {
		this.reNewPsw = reNewPsw;
	}
	public String getNewPsw() {
		return newPsw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
}
