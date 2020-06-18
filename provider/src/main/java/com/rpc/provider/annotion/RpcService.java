package com.rpc.provider.annotion;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author whq
 * @date 2020/6/18 17:36
 * @description 注入服务
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcService {
}
