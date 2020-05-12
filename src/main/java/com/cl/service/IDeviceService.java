package com.cl.service;

import com.cl.common.Page;
import com.cl.model.DeviceDO;

public interface IDeviceService {

    int insert(DeviceDO record);

    DeviceDO selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(DeviceDO deviceDO);

    Page<DeviceDO> pageDevice(String devNo, Integer curPage, Integer pageSize);
}
