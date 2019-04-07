package com.gameloft9.demo.dataaccess.model.system;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

;




/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component
@ServerEndpoint(value="/websocket",configurator=GetHttpSessionConfigurator.class)
public class WebSocketTest {
;


    /* MyThread thread1=new MyThread();
        Thread thread=new Thread(thread1);*/
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();


    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    private String uuid="";

    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(  Session session, EndpointConfig config){
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());


        this.session = session;
         this.uuid= (String)httpSession.getAttribute("sysUser");
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        System.out.println("有新连接加入！"+ uuid +"当前在线人数为" + getOnlineCount());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }



    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage( String message, Session session ) {
      /*  System.out.println("来自客户端的消息:" + message);
        //群发消息

      for(WebSocketTest item: webSocketSet){
                    item.sendMessage(message,sysNotify);
      }*/
    }



    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */

    /*
    * */

    public void sendMessage(String message, SysNotify sysNotify) {

      String receiverId =sysNotify.getReceiverId();
        for(WebSocketTest item: webSocketSet){
            /*   通过判断接收人和连接进该服务的人ID是否一致 如果一致则单点推送 */
                if(item.uuid.equals(receiverId)) {
                    item.session.getAsyncRemote().sendText(message);
                    /*  0这里代表全体信息 全体在线推送消息 */
                }else if(receiverId.equals("0")){
                    item.session.getAsyncRemote().sendText(message);
                }

        }

            
        }

     public void upDateState(String message,SysNotify sysNotify){
       String senderId= sysNotify.getSenderId();
         for(WebSocketTest item: webSocketSet){
             if (item.uuid.equals(senderId)){
                 item.session.getAsyncRemote().sendText(message);
             }
         }

     }


       public void sendToAll(String message){

       }

        
        //this.session.getAsyncRemote().sendText(message);
    

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketTest.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketTest.onlineCount--;
    }
}