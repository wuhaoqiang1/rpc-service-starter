package com.rpc.provider.service.handler;

import com.rpc.api.param.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author whq
 * @date 2020/6/18 16:09
 * @description rpc远程通信处理
 */
public class RpcInvokeHandler implements Runnable{

    private Socket socket;

    public RpcInvokeHandler(Socket socket) {
        this.socket = socket;
    }

    /**
     * 所有发布的服务，暂时不考虑方法名相同的情况
     */
    public static Map<String,Object> allService = new ConcurrentHashMap<>();

    private static Object invoke(RpcRequest rpcRequest) {
        try {
            Class czz = Class.forName(rpcRequest.getClassName());
            Method method = czz.getMethod(rpcRequest.getMethodName(),rpcRequest.getParamTypes());
            Object service = allService.get(rpcRequest.getClassName()+"."+rpcRequest.getMethodName());
            return method.invoke(service,rpcRequest.getArgs());

        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try{
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 读取客户端传递的对象
            RpcRequest rpcParam = (RpcRequest)objectInputStream.readObject();
            System.out.println("接收到参数："+rpcParam.toString());

            // 根据参数反射调用接口
            objectOutputStream.writeObject(RpcInvokeHandler.invoke(rpcParam));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
