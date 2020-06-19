package com.rpc.provider.service.handler;

import java.lang.reflect.Proxy;

/**
 * @author whq
 * @date 2020/6/18 15:44
 * @description
 */
public class RpcProxyClient {

    /**
     * 给接口创建代理类
     */
    public static <T>T createProxy(Class<T> czz,String host,int port) {
        return (T)Proxy.newProxyInstance(czz.getClassLoader(),new Class<?>[]{czz},new RpcProxyHandler(host,port));
    }

}
