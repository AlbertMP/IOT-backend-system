package com.cl.dao;

import com.cl.model.DeviceDO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class DeviceDAOTest {
    @Autowired
    private DeviceDAO deviceDAO;
    public static ObjectMapper objectMapper = new ObjectMapper();
    @Test
    public void insert() {
        DeviceDO deviceDO = new DeviceDO();
        deviceDO.setDevNo("011");
        deviceDO.setDevName("设备011");
        deviceDAO.insert(deviceDO);
    }

    @Test
    public void selectByPrimaryKey(){
        DeviceDO deviceDO = deviceDAO.selectByPrimaryKey(2L); // 2L = (Long)2
        System.out.println("设备编号：" + deviceDO.getDevNo());
    }

    @Test
    public void updateByPrimaryKey(){
        DeviceDO deviceDO = deviceDAO.selectByPrimaryKey(2L); // 2L = (Long)2
        deviceDO.setDevNo("1");
        deviceDO.setDevName("智能电灯001");
        deviceDAO.updateByPrimaryKey(deviceDO);
    }

    @Test
    public void deleteByPrimaryKey(){
        deviceDAO.deleteByPrimaryKey(2L);
    }

    @Test
    public void selectList() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("devNo", "002");
        List<DeviceDO> list = deviceDAO.selectList(map);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }

    @Test
    public void pagingQuery() throws JsonProcessingException {
        List<DeviceDO> list = deviceDAO.pagingQuery(0L);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }
}

