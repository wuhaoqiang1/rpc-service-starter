package com.rpc.provider.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: whq
 * @Date: 2020/06/18 23:04
 * @Description:
 */
@Data
@ConfigurationProperties(prefix = "rpc.service")
public class RpcProperties {

    /**
     * 调用服务的host
     */
    private String invokeHost;


    /**
     * 调用服务的port
     */
    private int invokePort;

    /**
     * 提供出去的服务的端口
     */
    private int registerPort;

}
