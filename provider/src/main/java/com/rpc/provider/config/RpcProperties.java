package com.rpc.provider.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: whq
 * @Date: 2020/06/18 23:04
 * @Description:
 */
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

    public String getInvokeHost() {
        return invokeHost;
    }

    public void setInvokeHost(String invokeHost) {
        this.invokeHost = invokeHost;
    }

    public int getInvokePort() {
        return invokePort;
    }

    public void setInvokePort(int invokePort) {
        this.invokePort = invokePort;
    }

    public int getRegisterPort() {
        return registerPort;
    }

    public void setRegisterPort(int registerPort) {
        this.registerPort = registerPort;
    }
}
