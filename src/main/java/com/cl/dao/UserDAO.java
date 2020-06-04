package com.cl.dao;

import com.cl.model.UserDO;
import java.util.List;
import java.util.Map;

public interface UserDAO {

    int insert(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKey(UserDO userDO);

    int deleteByPrimaryKey(Long id);

    int countList(Map<String, Object> map);

    List<UserDO> selectList(Map<String, Object> map);

    List<UserDO> pagingQuery(Long page);

    UserDO selectByNameAndPassword(Map<String, Object> map);
}
