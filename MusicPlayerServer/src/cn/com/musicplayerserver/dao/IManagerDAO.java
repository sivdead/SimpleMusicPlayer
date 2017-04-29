package cn.com.musicplayerserver.dao;

import java.util.List;

import cn.com.musicplayerserver.entity.Manager;

public interface IManagerDAO {
	public Manager findManagerByName(String name);
	public Boolean updateUser(Manager manager);
	public Boolean addManage(Manager manage);
	public List<Manager> findMangerBystr(String nameStr,int typeId);
	public Boolean deleteUser(Manager manager);
}
