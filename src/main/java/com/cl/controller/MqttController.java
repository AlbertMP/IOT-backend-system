package com.cl.controller;


import com.cl.common.Contants;
import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.service.IMqttService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mqtt")
public class MqttController {

    private Logger logger = LoggerFactory.getLogger(MqttController.class);

    @Autowired
    private IMqttService mqttService;

    @RequestMapping("/send")
    @ResponseBody
    public Result<Void> send(@RequestParam(value = "devNo", required = true) String devNo,
                             @RequestParam(value = "data", required = true) String data){
        Result<Void> result = new ResultSupport<>(true);
        try {
//            System.out.println(data);
            mqttService.pub(devNo+ Contants.MQTT_UP, data);
        }catch (Exception e){
            logger.error("发送失败", e);
            result.setSuccess(false);
            result.setMessage("发送失败" + e.getMessage());
        }
        return result;
    }
}
