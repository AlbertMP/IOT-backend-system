package com.cl.controller;


import com.cl.annotations.Permissions;
import com.cl.common.Contants;
import com.cl.common.Page;
import com.cl.common.Result;
import com.cl.common.ResultSupport;
import com.cl.enums.RoleEnum;
import com.cl.model.UserDO;
import com.cl.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping("/insert")
    @ResponseBody
    @Permissions(role = {RoleEnum.ADMIN})
//    public Result<Void> insert(String userNo, String userName, String userPwd) {
//        UserDO userDO = new UserDO();
//        userDO.setUserNo(userNo);
//        userDO.setUserName(userName);
//        userDO.setUserPwd(userPwd);
//        userService.insert(userDO);
//        Result<Void> result = new ResultSupport<>(true);
//
//        return result;
//    }
    public Result<Void> insert(@RequestParam(value = "userNo", required = true) String userNo,
                               @RequestParam(value = "userName", required = true) String userName,
                               @RequestParam(value = "userPwd", required = false, defaultValue = "123456") String userPwd,
                               @RequestParam(value = "roleKey", required = true) String roleKey) {
        UserDO userDO = new UserDO();
        userDO.setUserNo(userNo);
        userDO.setUserName(userName);
        userDO.setUserPwd(userPwd);
        userDO.setRoleKey(roleKey);
        userService.insert(userDO);
        Result<Void> result = new ResultSupport<>(true);

        return result;
    }

//    @RequestMapping("/delete/{id}")
//    @ResponseBody
//    public Result<Void> delete(@PathVariable("id") Long id) {
//        userService.deleteByPrimaryKey(id);
//        Result<Void> result = new ResultSupport<>(true);
//        return result;
//    }

    @RequestMapping("/delete")
    @ResponseBody
    @Permissions(role = {RoleEnum.ADMIN})
    public Result<Void> delete(Long id) {
        userService.deleteByPrimaryKey(id);
        Result<Void> result = new ResultSupport<>(true);
        return result;
    }

    @RequestMapping("/get/{id}")
    @ResponseBody
    public Result<UserDO> get(@PathVariable("id") Long id) {
        UserDO userDO = userService.selectByPrimaryKey(id);
        Result<UserDO> result = new ResultSupport<>(true);
        result.setDataObject(userDO);
        return result;
    }

//    @RequestMapping("/update/{id}")
//    @ResponseBody
//    public Result<Void> update(@PathVariable("id") Long id, String userNo, String userName) {
//        UserDO userDO = userService.selectByPrimaryKey(id);;
//        userDO.setUserNo(userNo);
//        userDO.setUserName(userName);
//        userService.updateByPrimaryKey(userDO);
//        Result<Void> result = new ResultSupport<>(true);
//
//        return result;
//    }

    @RequestMapping("/update")
    @ResponseBody
    @Permissions(role = {RoleEnum.ADMIN})
//    public Result<Void> update(Long id, String userNo, String userName, String userPwd) {
//        UserDO userDO = new UserDO();
//        userDO.setId(id);
//        userDO.setUserNo(userNo);
//        userDO.setUserName(userName);
//        userDO.setUserPwd(userPwd);
//        userService.updateByPrimaryKey(userDO);
//        Result<Void> result = new ResultSupport<>(true);
//        return result;
//    }
    public Result<Void> update(@RequestParam(value = "id", required = true) Long id,
                               @RequestParam(value = "userNo", required = true) String userNo,
                               @RequestParam(value = "userName", required = true) String userName,
                               @RequestParam(value = "userPwd", required = true) String userPwd,
                               @RequestParam(value = "roleKey", required = true) String roleKey) {
        UserDO userDO = userService.selectByPrimaryKey(id);
        userDO.setUserNo(userNo);
        userDO.setUserName(userName);
        userDO.setUserPwd(userPwd);
        userDO.setRoleKey(roleKey);
        userService.updateByPrimaryKey(userDO);
        Result<Void> result = new ResultSupport<>(true);

        return result;
    }

    @RequestMapping("/pageUser")
    @ResponseBody
    @Permissions(role = {RoleEnum.ADMIN, RoleEnum.NORMAL})
    public Map<String, Object> pageUser(@RequestParam(value = "userNo", required = false) String userNo,
                                          @RequestParam(value = "page", required = false, defaultValue = "1") int curPage,
                                          @RequestParam(value = "rows", required = false, defaultValue = "10") int pageSize){
        Page<UserDO> pageUser = userService.pageUser(userNo, curPage, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("total", pageUser.getTotalNumber());
        map.put("rows", pageUser.getItems());
        return map;
    }

    @RequestMapping("/login")
    @ResponseBody
    @Permissions(role = {RoleEnum.ADMIN, RoleEnum.NORMAL})
    public Result<Void> login(@RequestParam(value = "userName", required = true) String userName,
                              @RequestParam(value = "userPwd", required = true) String userPwd,
                              HttpServletRequest request){  // 加入request用于取出Session
        UserDO userDO = userService.selectByUserNameAndPassword(userName, userPwd);
        Result<Void> result = new ResultSupport<Void>(true);
        if (userDO == null){
            result.setSuccess(false);
            result.setMessage("用户名或密码有误");
        }else{
            // 登陆成功，向Session放入用户信息
            HttpSession session = request.getSession();
            session.setAttribute(Contants.USER_SESSION, userDO);
            session.setMaxInactiveInterval(600);    // 设置超时时间10分钟，若用户10分钟内不操作，则销毁Session，防止内存溢出
        }
        return result;
    }

    @RequestMapping("/logout")
    @ResponseBody
    @Permissions(role = {RoleEnum.ADMIN, RoleEnum.NORMAL})
    public void logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            request.getRequestDispatcher("/mvc/loginIndex").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
