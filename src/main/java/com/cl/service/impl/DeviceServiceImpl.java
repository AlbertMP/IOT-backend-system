package com.cl.service.impl;

import com.cl.common.Page;
import com.cl.dao.DeviceDAO;
import com.cl.model.DeviceDO;
import com.cl.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Autowired
    private DeviceDAO deviceDAO;

    @Override
    public int insert(DeviceDO record) {
        return deviceDAO.insert(record);
    }

    @Override
    public DeviceDO selectByPrimaryKey(Long id) {
        return deviceDAO.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return deviceDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(DeviceDO record) {
        return deviceDAO.updateByPrimaryKey(record);
    }

    @Override
    public Page<DeviceDO> pageDevice(String devNo, Integer curPage, Integer pageSize) {
        Page<DeviceDO> page = new Page<>();
        page.setCurrentIndex(curPage);
        page.setPageSize(pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("devNo", devNo);
        int total = deviceDAO.countList(map);
        List<DeviceDO> list = deviceDAO.selectList(map);
        page.setTotalNumber(total);
        page.setItems(list);
        return page;

    }
}
