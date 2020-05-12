package com.cl.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.cl.model.UserDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;
    public static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void insert() {
        UserDO userDO = new UserDO();
        userDO.setUserNo("009");
        userDO.setUserName("小杨");
        userDO.setUserPwd("555555");
        userDAO.insert(userDO);
    }

    @Test
    public void selectByPrimaryKey(){
        UserDO userDO = userDAO.selectByPrimaryKey(1L); // 2L = (Long)2
        System.out.println("用户：" + userDO.getUserName());
    }

    @Test
    public void updateByPrimaryKey(){
        UserDO userDO = userDAO.selectByPrimaryKey(1L); // 2L = (Long)2
        userDO.setUserName("小王");
        userDO.setUserPhone("17374131111");
        userDAO.updateByPrimaryKey(userDO);
    }

    @Test
    public void deleteByPrimaryKey(){
        userDAO.deleteByPrimaryKey(9L);
    }

    @Test
    public void pagingQuery() throws JsonProcessingException {
        List<UserDO> list = userDAO.pagingQuery(0L);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list));
    }
}