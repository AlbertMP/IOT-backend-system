package com.cl.interceptor;

import com.cl.common.Contants;
import com.cl.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());   // 记录日志

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("用户登录拦截");
        HttpSession session = request.getSession(); // 从Session取出用户信息
        UserDO userDO = (UserDO) session.getAttribute(Contants.USER_SESSION);
        if (userDO != null){
            return true;
        }
        request.getRequestDispatcher("/mvc/loginIndex").forward(request, response); // 若不存在用户信息，则要求用户登录
        return false;
    }
}
