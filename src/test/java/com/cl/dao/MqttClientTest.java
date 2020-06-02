package com.cl.dao;

import com.cl.common.Contants;
import com.cl.enums.DeviceStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static com.cl.dao.DeviceDAOTest.objectMapper;

public class MqttClientTest {

    // 客户端设定

    private static final String HOST = "tcp://127.0.0.1:1883";
    // 客户端唯一标识
    private static final String CLIENTID = "client2";
    private static final String USERNAME = "test";
    private static final String PASSWORD = "test";

    // 选择数据库中的某个设备的dev_no
    private static final String DEV_NO = "001";

    // 定义客户端实例
    private MqttClient mqttClient;


    // 设置连接方法

    public void connect() throws MqttException {
        mqttClient = new MqttClient(HOST, CLIENTID, new MemoryPersistence());
        // 连接参数配置
        MqttConnectOptions options = new MqttConnectOptions();
        // true表示每次连接创建新的session
        options.setCleanSession(true);
        options.setUserName(USERNAME);
        options.setPassword(PASSWORD.toCharArray());
        options.setConnectionTimeout(180);
        options.setKeepAliveInterval(180);
        options.setAutomaticReconnect(true);
        // 通过上行通道发送消息
        options.setWill(DEV_NO + Contants.MQTT_UP, DeviceStatusEnum.OFFLINE.name().getBytes(), 0, true);

        // 设置回调信息
        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.out.println("连接断开，可以重连");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                System.out.println("topic:" + topic);
                System.out.println("message:" + message);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                System.out.println("发送成功后回调");
            }
        });

        mqttClient.connect(options);
        System.out.println("连接成功");
    }


    // 设置订阅和发布方法

    public void sub(String topic) throws MqttException{
        // 订阅
        mqttClient.subscribe(topic, 2);
    }

    public void pub(String topic, String msg) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        // 0表示只发送一次
        mqttMessage.setQos(0);
        mqttMessage.setPayload(msg.getBytes());
        MqttTopic mqttTopic = mqttClient.getTopic(topic);
        MqttDeliveryToken token = mqttTopic.publish(mqttMessage);
        token.waitForCompletion();
    }

    public static void main(String[] args) throws Exception {
        MqttClientTest client = new MqttClientTest();
        client.connect();
//        String topic = DEV_NO + Contants.MQTT_UP;
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("status", 1);
////        mqttClientTest.sub(topic);
//
//
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    client.pub(topic, objectMapper.writeValueAsString(dataMap));
//                } catch (MqttException e) {
//                    e.printStackTrace();
//                } catch (JsonProcessingException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, 1000, 10000);
        String topic = DEV_NO + Contants.MQTT_DW;
        client.sub(topic);

    }

}
