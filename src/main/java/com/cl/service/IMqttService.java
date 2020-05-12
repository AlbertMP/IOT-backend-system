package com.cl.service;

import org.eclipse.paho.client.mqttv3.MqttException;

public interface IMqttService {

    /**
     * 初始化
     * @throws MqttException
     */
    public void init() throws MqttException;

    /**
     * 订阅
     * @param topic
     * @throws MqttException
     */
    public void sub(String topic) throws MqttException;

    /**
     * 发布
     * @param topic
     * @param msg
     * @throws MqttException
     */
    public void pub(String topic, String msg) throws MqttException;
}
