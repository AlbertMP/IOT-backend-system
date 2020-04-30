package com.cl.service.impl;

import com.cl.common.Page;
import com.cl.dao.UserDAO;
import com.cl.model.UserDO;
import com.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserDAO userDAO;

    @Override
    public int insert(UserDO record) {
        return userDAO.insert(record);
    }

    @Override
    public UserDO selectByPrimaryKey(Long id) {
        return userDAO.selectByPrimaryKey(id);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UserDO record) {
        return userDAO.updateByPrimaryKey(record);
    }

    @Override
    public Page<UserDO> pageUser(String userNo, Integer curPage, Integer pageSize) {
        Page<UserDO> page = new Page<>();
        page.setCurrentIndex(curPage);
        page.setPageSize(pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("page", page);
        map.put("userNo", userNo);
        int total = userDAO.countList(map);
        List<UserDO> list = userDAO.selectList(map);
        page.setTotalNumber(total);
        page.setItems(list);
        return page;
    }

    @Override
    public UserDO selectByUserNameAndPassword(String userName, String userPwd){
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("userPwd", userPwd);
        return userDAO.selectByNameAndPassword(map);
    }
}
