package com.rpc.provider.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @Author: whq
 * @Date: 2020/06/18 23:03
 * @Description: starter≈‰÷√¿‡
 */
@Configurable
@EnableConfigurationProperties(RpcProperties.class)
public class RpcStarterConfig {

    @Autowired
    private RpcProperties rpcProperties;

    @Bean
    @ConditionalOnMissingBean(RpcServiceConfig.class)
    private RpcServiceConfig rpcServiceConfig() {
        return new RpcServiceConfig(rpcProperties);
    }

    @Bean
    @ConditionalOnMissingBean(RpcServerInitial.class)
    private RpcServerInitial rpcServerInitial() {
        return new RpcServerInitial(rpcProperties);
    }

    @Bean
    @ConditionalOnMissingBean(RegisterRpcServiceConfig.class)
    private RegisterRpcServiceConfig registerRpcServiceConfig() {
        return new RegisterRpcServiceConfig();
    }

}
