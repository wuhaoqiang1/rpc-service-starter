package com.rpc.provider.config;

import com.rpc.provider.annotion.RpcReference;
import com.rpc.provider.service.handler.RpcInvokeHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

/**
 * @author whq
 * @date 2020/6/18 17:02
 * @description 注册rpc服务
 */
@Component
public class RegisterRpcServiceConfig implements BeanPostProcessor {

    /**
     * 注册所有RpcService的服务
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class czz = bean.getClass();
        if(czz.isAnnotationPresent(RpcReference.class)) {
            // 记录下所有服务和bean的关系
            for(Method method : czz.getDeclaredMethods()) {
                // TODO 暂时直接取实现的第一个接口
                RpcInvokeHandler.allService.put(czz.getInterfaces()[0].getName()+"."+method.getName(),bean);
            }
        }

        return null;
    }
}
