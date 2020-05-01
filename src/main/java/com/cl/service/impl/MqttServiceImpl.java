package com.cl.service.impl;

import com.cl.common.Contants;
import com.cl.dao.DeviceDAO;
import com.cl.model.DeviceDO;
import com.cl.service.IMqttService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.List;

public class MqttServiceImpl implements IMqttService, InitializingBean {

    // 从mqtt配置文件里去数据
    @Value(value = "${mqtt.host}")
    private String host;
    @Value(value = "${mqtt.clientId}")
    private String clientId;
    @Value(value = "${mqtt.username}")
    private String username;
    @Value(value = "${mqtt.password}")
    private String password;

    @Autowired
    private DeviceDAO deviceDAO;

    // 客户端实例
    private MqttClient mqttClient;

    @Override
    public void init() throws MqttException {
        connect();
        // 把所有设备做订阅功能
        List<DeviceDO> deviceDOList = deviceDAO.selectList(new HashMap<>());
        for (DeviceDO deviceDO : deviceDOList){
            sub(deviceDO.getDevNo() + Contants.MQTT_UP);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    @Override
    public void sub(String topic) throws MqttException {
        mqttClient.subscribe(topic, 2);
    }

    @Override
    public void pub(String topic, String msg) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        // pub时指定的qos是服务器肯定按此规则接受，但是最终订阅者不一定。
        mqttMessage.setQos(2);
        mqttMessage.setPayload(msg.getBytes());
        MqttTopic mqttTopic = mqttClient.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }

    private void connect() throws MqttException{
        mqttClient = new MqttClient(host, clientId, new MemoryPersistence());
        // 设置连接时的参数
        MqttConnectOptions options = new MqttConnectOptions();
        // 是否清空session，如果设置为false表示服务器会保留客户端的连接记录
        // 设置为true表示每次连接到服务器都以新的身份连接
        options.setCleanSession(true);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        options.setConnectionTimeout(100);
        options.setKeepAliveInterval(180);
        // 掉线自动重连
        options.setAutomaticReconnect(true);
//        // 意义不大
//        /*
//        遗嘱消息：当连接断开时发送的死亡预告，此客户端连接断开后，
//        服务器会把此消息推送给订阅了此主题的客户机
//         */
//        options.setWill("close", "offline".getBytes(), 0, true);
        // 设置回调函数
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("连接断开，可以做重连");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("收到消息主题：" + topic);
                System.out.println("收到消息内容：" + message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("发布完成");
            }
        });

        mqttClient.connect(options);
        System.out.println("连接成功");
    }

}
