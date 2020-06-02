package com.cl.controller;


import com.cl.common.Page;
import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.enums.DeviceTypeEnum;
import com.cl.model.DeviceDO;
import com.cl.service.IDeviceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IDeviceService deviceService;

    @RequestMapping("/insert")
    @ResponseBody
    public Result<Void> insert(Integer devType, String devNo, String devName) {

        Result<Void> result = new ResultSupport<>(true);

        try {
            DeviceTypeEnum deviceTypeEnum = DeviceTypeEnum.getDevTypeStatus(devType);
            DeviceDO deviceDO = new DeviceDO();
            deviceDO.setDevType(deviceTypeEnum.getValue());
            deviceDO.setDevNo(devNo);
            deviceDO.setDevName(devName);
            deviceDO.setDevImg(deviceTypeEnum.getImg());
            deviceService.insert(deviceDO);
        }catch (Exception e){
            log.error("设备新增失败", e);
            result.setSuccess(false);
            result.setMessage("新增失败" + e.getMessage());
        }
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
    public Result<Void> update(Long id, Integer devType, String devNo, String devName) {
        Result<Void> result = new ResultSupport<>(true);

        try {
            DeviceTypeEnum deviceTypeEnum = DeviceTypeEnum.getDevTypeStatus(devType);
            DeviceDO deviceDO = deviceService.selectByPrimaryKey(id);
            deviceDO.setDevType(deviceTypeEnum.getValue());
            deviceDO.setDevNo(devNo);
            deviceDO.setDevName(devName);
            deviceDO.setDevImg(deviceTypeEnum.getImg());
            deviceService.updateByPrimaryKey(deviceDO);
        }catch (Exception e){
            log.error("设备更新失败", e);
            result.setSuccess(false);
            result.setMessage("更新失败" + e.getMessage());
        }
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
