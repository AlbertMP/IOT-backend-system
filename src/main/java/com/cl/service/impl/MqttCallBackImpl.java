package com.cl.service.impl;

import com.cl.common.Contants;
import com.cl.dao.DeviceDAO;
import com.cl.enums.DeviceStatusEnum;
import com.cl.model.DeviceDO;
import com.cl.model.DeviceDataDO;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MqttCallBackImpl implements MqttCallback {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceDAO deviceDAO;

    @Autowired
    private MongoOperations mongoTemplate;

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("连接断开，可以做重连");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.info("收到消息主题：" + topic);
        logger.info("收到消息内容：" + message);
        String devNo = topic.split(Contants.MQTT_UP)[0];
        DeviceDO deviceDO = deviceDAO.selectByDevNo(devNo);
        if (deviceDO == null){
            throw new RuntimeException("找不到对应的设备");
        }

        // 插入mongodb
        String data = message.toString();
        DeviceDataDO deviceDataDO= new DeviceDataDO();
        deviceDataDO.setDeviceId(deviceDO.getId());
        deviceDataDO.setData(data);
        deviceDataDO.setUploadDate(new Date());
        mongoTemplate.insert(deviceDataDO);

        System.out.println(data);
        System.out.println(data.contains(DeviceStatusEnum.OFFLINE.getValue().toString()));
        // 更新状态
        if (data.contains(DeviceStatusEnum.OFFLINE.getValue().toString())){
            System.out.println();
            deviceDO.setDevStatus(DeviceStatusEnum.OFFLINE.getValue());
        }else {
            deviceDO.setDevStatus(DeviceStatusEnum.ONLINE.getValue());
        }
        deviceDAO.updateByPrimaryKey(deviceDO);

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("发布完成");
    }
}
