package com.casic.websocket.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;

import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
 
public class MyWebSocketClient extends WebSocketClient{
 
    public MyWebSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }
 
    @Override
    public void onOpen(ServerHandshake shake) {
        System.out.println("握手...");
        for(Iterator<String> it=shake.iterateHttpFields();it.hasNext();) {
            String key = it.next();
            System.out.println(key+":"+shake.getFieldValue(key));
        }
    }
 
    @Override
    public void onMessage(String paramString) {
        System.out.println("接收到消息："+paramString);
    }
 
    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        System.out.println("关闭...");
    }
 
    @Override
    public void onError(Exception e) {
        System.out.println("异常"+e);
        
    }
    
    public static void main(String[] args) {
        try {
            MyWebSocketClient client = new MyWebSocketClient("ws://localhost:10086/websocket/192.168.25.128/root/123456");
            client.connect();
            while (!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)) {
                System.out.println("还没有打开");
            }
            System.out.println("建立websocket连接");
            client.send("asd");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } 
    }

}