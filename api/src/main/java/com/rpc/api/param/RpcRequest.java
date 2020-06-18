package com.rpc.api.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author whq
 * @date 2020/6/18 11:54
 * @description
 */
@Data
public class RpcRequest implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 要调用的类名
     */
    private String className;

    /**
     * 要调用的方法名
     */
    private String methodName;

    /**
     * 调用方法传递的所有参数
     */
    private Object[] args;

    /**
     * 调用方法的参数类型列表
     */
    private Class[] paramTypes;

}
