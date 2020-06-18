package com.rpc.provider.annotion;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author whq
 * @date 2020/6/18 16:59
 * @description 标记需要发布的服务
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RpcReference {
}
