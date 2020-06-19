package com.rpc.provider.config;

import com.rpc.provider.service.handler.RpcInvokeHandler;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author whq
 * @date 2020/6/18 17:17
 * @description 启动rpc服务
 */
public class RpcServerInitial {

    private int port;

    private final ExecutorService executorService= Executors.newCachedThreadPool();

    public RpcServerInitial(RpcProperties rpcProperties) {
        this.port = rpcProperties.getRegisterPort();
    }

    /**
     * 初始化ServiceSocket
     */
    @PostConstruct
    public void initServiceSocket() {
        //启动服务
        executorService.execute(()->{
            try(ServerSocket serverSocket = new ServerSocket(port)) {
                while(true) {
                    // 阻塞连接
                    Socket socket = serverSocket.accept();
                    // 请求全部给线程池处理
                    executorService.submit(new RpcInvokeHandler(socket));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
