package com.cl.service;

import com.cl.common.Page;
import com.cl.model.UserDO;

public interface IUserService {

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int deleteByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO userDO);

    Page<UserDO> pageUser(String userNo, Integer curPage, Integer pageSize);

    UserDO selectByUserNameAndPassword(String userName, String userPwd);
}
