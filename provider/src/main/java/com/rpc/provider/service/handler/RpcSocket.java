package com.rpc.provider.service.handler;

import com.rpc.api.param.RpcRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author whq
 * @date 2020/6/18 14:43
 * @description
 */
@Data
@AllArgsConstructor
public class RpcSocket {

    private String host;

    private int port;

    public Object send(RpcRequest rpcRequest) {
        ObjectOutputStream objectOutputStream = null;
        ObjectInputStream objectInputStream = null;
        try(Socket socket = new Socket(host,port);) {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            Object result = objectInputStream.readObject();
            return result;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
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
        return "error";
    }

}
