package com.arimsky.blogapi.interceptor;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.base.ErrorCode;
import com.arimsky.blogapi.base.ResultData;
import com.arimsky.blogapi.pojo.entity.SysUser;
import com.arimsky.blogapi.service.LoginService;
import com.arimsky.blogapi.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginInterceptor
 * @Author: aRimsiky
 * @Date: 2021/11/01
 * @Description 登录拦截器
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        //handler 可能是 RequestResourceHandler springboot 程序
        // 访问静态资源 默认去classpath下的static目录去查询
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //如果方法有JwtIgnore注解，直接通过
/*        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method=handlerMethod.getMethod();
        if (method.isAnnotationPresent(JwtIgnore.class)) {
            JwtIgnore jwtIgnore = method.getAnnotation(JwtIgnore.class);
            if(jwtIgnore.value()){
                return true;
            }
        }*/
        String token = request.getHeader("Authorization");
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");

        // 没有token 或者 有token 拿不到user 都不放行
        if (token == null) {
            ResultData result = ResultData.error(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null) {
            ResultData result = ResultData.error(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        //是登录状态，放行
        // 将用户信息存到线程中, 在controller中 直接获取用户的信息
        UserThreadLocal.put(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        UserThreadLocal.remove();
    }
}
