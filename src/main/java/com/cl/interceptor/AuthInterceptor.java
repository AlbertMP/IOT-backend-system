package com.cl.interceptor;

import com.cl.annotations.Permissions;
import com.cl.common.Contants;
import com.cl.enums.RoleEnum;
import com.cl.model.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

public class AuthInterceptor implements HandlerInterceptor {

    private static Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("权限拦截");

        HttpSession session = request.getSession();
        UserDO userDO = (UserDO) session.getAttribute(Contants.USER_SESSION);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        Permissions annotations = method.getAnnotation(Permissions.class);
        for (RoleEnum roleEnum: annotations.role()) {
            if (roleEnum.name().equals(userDO.getRoleKey())){
                return true;
            }
        }

        return false;

    }
}
