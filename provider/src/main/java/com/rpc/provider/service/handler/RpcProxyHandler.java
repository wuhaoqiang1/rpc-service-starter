package com.rpc.provider.service.handler;

import com.rpc.api.param.RpcRequest;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author whq
 * @date 2020/6/18 15:43
 * @description
 */
public class RpcProxyHandler implements InvocationHandler {

    private String host;

    private int port;

    public RpcProxyHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcSocket rpcSocket = new RpcSocket(host,port);
        RpcRequest rpcRequest = new RpcRequest();

        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setArgs(args);
        rpcRequest.setParamTypes(method.getParameterTypes());

        return rpcSocket.send(rpcRequest);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
