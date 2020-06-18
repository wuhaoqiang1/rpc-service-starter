package com.rpc.provider.service.handler;

import com.rpc.api.param.RpcRequest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author whq
 * @date 2020/6/18 15:43
 * @description
 */
@Data
@AllArgsConstructor
public class RpcProxyHandler implements InvocationHandler {

    private String host;

    private int port;

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
}
