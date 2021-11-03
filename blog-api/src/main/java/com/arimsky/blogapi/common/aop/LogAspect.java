package com.arimsky.blogapi.common.aop;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.utils.HttpContextUtils;
import com.arimsky.blogapi.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志切面
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     * <p>
     * 1、execution(public * *(..)) 任意的公共方法
     * 2、execution（* set*（..）） 以set开头的所有的方法
     * 3、execution（* com.lingyejun.annotation.LoggerApply.*（..））com.lingyejun.annotation.LoggerApply这个类里的所有的方法
     * 4、execution（* com.lingyejun.annotation.*.*（..））com.lingyejun.annotation包下的所有的类的所有的方法
     * 5、execution（* com.lingyejun.annotation..*.*（..））com.lingyejun.annotation包及子包下所有的类的所有的方法
     * 6、execution(* com.lingyejun.annotation..*.*(String,?,Long)) com.lingyejun.annotation包及子包下所有的类的有三个参数，第一个参数为String类型，第二个参数为任意类型，第三个参数为Long类型的方法
     * 7、execution(@annotation(com.lingyejun.annotation.Lingyejun)) 用于匹配当前执行方法持有指定注解的方法；
     */
    @Pointcut("@annotation(com.arimsky.blogapi.common.aop.LogAnnotation)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        // execute method
        Object proceed = point.proceed();

        long endTime = System.currentTimeMillis();

        recordLog(point, beginTime - endTime);

        return proceed;
    }

    private void recordLog(ProceedingJoinPoint point, long executionTime) {

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start==============================");
        log.info("module:{}", logAnnotation.module());
        log.info("operation:{}", logAnnotation.operation());

        // 请求的方法名
        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}", className + "." + methodName + "()");

        // 请求的参数
        Object[] args = point.getArgs();
        if (args == null || args.length == 0) {
            args = new Object[]{"no args"};
        }
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}", params);


/*        // 获取请求的ip地址
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes.getRequest();*/
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ipAddr = IpUtils.getIpAddr(request);
        log.info("ip :{}", ipAddr);

        log.info("excute time : {} ms", executionTime);
        log.info("=====================log end================================");
    }

}
