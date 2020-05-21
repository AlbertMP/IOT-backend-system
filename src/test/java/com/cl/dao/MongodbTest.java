package com.cl.dao;


import com.cl.model.DeviceDataDO;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")

public class MongodbTest {

    private Logger logger = LoggerFactory.getLogger(MongodbTest.class);

    @Autowired
    private MongoOperations mongoTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void insert() throws IOException {
        DeviceDataDO deviceDataDO = new DeviceDataDO();
        deviceDataDO.setDeviceId(6L);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 1);
        deviceDataDO.setData(objectMapper.writeValueAsString(map));
        deviceDataDO.setUploadDate(new Date());
        mongoTemplate.insert(deviceDataDO);
    }

    @Test
    public void find() throws IOException {
        DeviceDataDO deviceDataDO = mongoTemplate.findOne(Query.query(Criteria.where("deviceId").is(6L)), DeviceDataDO.class);
        logger.info("查询：" + objectMapper.writeValueAsString(deviceDataDO));
    }

    @Test
    public void update() throws IOException{
        mongoTemplate.updateFirst(Query.query(Criteria.where("deviceId").is(6L)), Update.update("uploadDate", new Date()), DeviceDataDO.class);
        find();
    }

    @Test
    public void delete() throws IOException{
        mongoTemplate.remove(Query.query(Criteria.where("deviceId").is(6L)), DeviceDataDO.class);
        find();
    }
}
