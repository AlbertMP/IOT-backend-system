package com.cl.controller;


import com.cl.common.Page;
import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.model.DeviceDO;
import com.cl.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/insert")
    @ResponseBody
    public Result<Void> insert(String devNo, String devName) {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDevNo(devNo);
        deviceDO.setDevName(devName);
        deviceService.insert(deviceDO);
        Result<Void> result = new ResultSupport<>(true);

        return result;
    }

//    @RequestMapping("/delete/{id}")
//    @ResponseBody
//    public Result<Void> delete(@PathVariable("id") Long id) {
//        deviceService.deleteByPrimaryKey(id);
//        Result<Void> result = new ResultSupport<>(true);
//        return result;
//    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Void> delete(Long id) {
        deviceService.deleteByPrimaryKey(id);
        Result<Void> result = new ResultSupport<>(true);
        return result;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Result<DeviceDO> get(@PathVariable("id") Long id) {
        DeviceDO deviceDO = deviceService.selectByPrimaryKey(id);
        Result<DeviceDO> result = new ResultSupport<>(true);
        result.setDataObject(deviceDO);
        return result;
    }

//    @RequestMapping("/update/{id}")
//    @ResponseBody
//    public Result<Void> update(@PathVariable("id") Long id, String devNo, String devName) {
//        DeviceDO deviceDO = deviceService.selectByPrimaryKey(id);;
//        deviceDO.setDevNo(devNo);
//        deviceDO.setDevName(devName);
//        deviceService.updateByPrimaryKey(deviceDO);
//        Result<Void> result = new ResultSupport<>(true);
//
//        return result;
//    }

    @RequestMapping("/update")
    @ResponseBody
    public Result<Void> update(Long id, String devNo, String devName) {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setId(id);
        deviceDO.setDevNo(devNo);
        deviceDO.setDevName(devName);
        deviceService.updateByPrimaryKey(deviceDO);
        Result<Void> result = new ResultSupport<>(true);
        return result;
    }

    @RequestMapping("/pageDevice")
    @ResponseBody
    public Map<String, Object> pageDevice(@RequestParam(value = "devNo", required = false) String devNo,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int curPage,
                                          @RequestParam(value = "rows", required = false, defaultValue = "10") int pageSize){
        Page<DeviceDO> pageDevice = deviceService.pageDevice(devNo, curPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageDevice.getTotalNumber());
        map.put("rows", pageDevice.getItems());
        return map;
    }

}
