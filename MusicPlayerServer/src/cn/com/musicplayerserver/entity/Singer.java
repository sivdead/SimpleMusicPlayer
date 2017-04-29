package cn.com.musicplayerserver.entity;

import java.sql.Date;

public class Singer {
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
		Singer other = (Singer) obj;
		if (singerId == null) {
			if (other.singerId != null)
				return false;
		} else if (!singerId.equals(other.singerId))
			return false;
		return true;
	}
	private Integer singerId;
	public Integer getSexId() {
		return SexId;
	}
	public void setSexId(Integer sexId) {
		SexId = sexId;
	}
	private String singerName;
	private String intro;
	private Integer SexId;
	private Integer nationId;
	private Date birthday;
	private Integer spId;
	private Integer sysSpId;
	public Integer getSpId() {
		return spId;
	}
	public void setSpId(Integer spId) {
		this.spId = spId;
	}
	public Integer getSysSpId() {
		return sysSpId;
	}
	public void setSysSpId(Integer sysSpId) {
		this.sysSpId = sysSpId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Integer getNationId() {
		return nationId;
	}
	public void setNationId(Integer nationId) {
		this.nationId = nationId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date bir) {
		this.birthday = bir;
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
	 * @return the singerName
	 */
	public String getSingerName() {
		return singerName;
	}
	/**
	 * @param singerName the singerName to set
	 */
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
}
