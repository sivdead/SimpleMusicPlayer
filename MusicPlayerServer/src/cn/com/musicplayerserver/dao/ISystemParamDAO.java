package cn.com.musicplayerserver.dao;

import java.util.List;

import cn.com.musicplayerserver.entity.SystemParam;

public interface ISystemParamDAO {
	public List<SystemParam> selectSystemParam(String str);
	public SystemParam getSystemParamById(int id);
	public SystemParam getSystemParamByNameAndValue(String Name, String Value);
}
