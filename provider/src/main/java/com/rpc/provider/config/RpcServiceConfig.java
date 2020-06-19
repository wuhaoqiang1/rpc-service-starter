package com.rpc.provider.config;


import com.rpc.provider.annotion.RpcService;
import com.rpc.provider.service.handler.RpcProxyClient;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * @author whq
 * @date 2020/6/18 17:41
 * @description 注入RpcService接口
 */
public class RpcServiceConfig implements BeanPostProcessor {

    private String host;

    private int port;

    public RpcServiceConfig(RpcProperties rpcProperties) {
        this.host = rpcProperties.getInvokeHost();
        this.port = rpcProperties.getInvokePort();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // bean初始化之前把要注入的服务初始化对应的动态代理对象
        for (Field field : bean.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(RpcService.class)) {
                field.setAccessible(true);
                Object service = RpcProxyClient.createProxy(field.getType(),host,port);
                try {
                    field.set(bean,service);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }
}
