package com.cl.dao;

import com.cl.model.DeviceDO;

import java.util.List;
import java.util.Map;

public interface DeviceDAO {

    int insert(DeviceDO record);

    DeviceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(DeviceDO deviceDO);

    int deleteByPrimaryKey(Long id);

    List<DeviceDO> selectList(Map<String, Object> map);

    int countList(Map<String, Object> map);

    List<DeviceDO> pagingQuery(Long page);

    DeviceDO selectByDevNo(String devNo);
}
