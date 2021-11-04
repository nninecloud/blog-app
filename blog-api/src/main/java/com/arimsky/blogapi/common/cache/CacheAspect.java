package com.arimsky.blogapi.common.cache;

import com.alibaba.fastjson.JSON;
import com.arimsky.blogapi.base.ResultData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.time.Duration;

/**
 * @ClassName: CacheAspect
 * @Author: aRimsiky
 * @Date: 2021/11/04
 * @Description CacheAspect 增强切面类
 */

@Aspect
@Component
@Slf4j
public class CacheAspect {

    /**
     * 存储上一次的key 值,使得在插入时,能够找到上一次的数据在redis中清除掉
     */
//    private volatile Map<String, String> preKeys = new HashMap<>();
    private volatile int flag = 0;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 所有有 @Cache 注解的都会被增强
     */
    @Pointcut("@annotation(com.arimsky.blogapi.common.cache.Cache)")
    public void cacheAspect() {

    }

    /**
     * 对方法环绕增强
     * <p>
     * redis 初始没有数据可能会有bug, 插入不能立刻刷新 没解决 ❎
     */
    @Around("cacheAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            // getSignature());是获取到这样的信息 :修饰符+ 包名+组件名(类名) +方法名
            Signature signature = joinPoint.getSignature();
            // 拿到目标对象，获取类名
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = signature.getName();

            // 每个参数的类型数据
            //noinspection rawtypes
            Class[] parameterTypes = new Class[joinPoint.getArgs().length];
            Object[] args = joinPoint.getArgs();
//            for (Object arg : args) {
//                System.out.println(arg + "  *******");
//            }
            // 拼接参数
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                if (args[i] != null) {
                    params.append(JSON.toJSONString(args[i]));
                    parameterTypes[i] = args[i].getClass();
                } else {
                    parameterTypes[i] = null;
                }
            }

            if (StringUtils.hasText(params.toString())) {
                //
                params = new StringBuilder(DigestUtils.md5Hex(params.toString()));
            }

            // 获取增强方法
            //noinspection unchecked
            Method method = joinPoint.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);

            //获取注解
            Cache annotation = method.getAnnotation(Cache.class);
            // 缓存过期时间
            long expire = annotation.expire();
            // 缓存名
            String name = annotation.name();
            String paramskey = params.toString();
            // 从redis 中获取, 拿不到 将数据放入redis
            String rediskey = name + "::" + className + "::" + methodName + "::" + paramskey;

            if (flag == 1){
                flag = 0;
                Object proceed = joinPoint.proceed();

                redisTemplate.opsForValue().set(rediskey, JSON.toJSONString(proceed), Duration.ofMillis(expire));
                log.info("存入redis 缓存{}, {}", className, methodName);

                return proceed;
            }

            // 如果时写文章的更新操作要对redis 进行更新
            if ("ArticleController".equals(className) && "publish".equals(methodName)) {
                flag = 1;
                return joinPoint.proceed();
            }



            String redisValue = redisTemplate.opsForValue().get(rediskey);

            if (StringUtils.hasText(redisValue)) {
                log.info("redis 缓存加载{}, {}", className, methodName);
                return JSON.parseObject(redisValue, ResultData.class);
            }

            Object proceed = joinPoint.proceed();

            redisTemplate.opsForValue().set(rediskey, JSON.toJSONString(proceed), Duration.ofMillis(expire));
            log.info("存入redis 缓存{}, {}", className, methodName);

            return proceed;

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return ResultData.error(-999, "系统错误");
    }


}
