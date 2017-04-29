package cn.com.musicplayerserver.vo;

import cn.com.musicplayerserver.entity.Singer;

public class SingerTableVO {
	private String singerName;
	private String sex;
	private String nation;
	private Singer singer;
	
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
}
