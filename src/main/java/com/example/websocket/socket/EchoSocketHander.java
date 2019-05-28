package com.example.websocket.socket;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


@Component
@Log4j2
public class EchoSocketHander extends TextWebSocketHandler {

    //在线用户列表
    private static final Map<String, WebSocketSession> users = new HashMap<>();

    /**
     * 连接成功时候，会触发页面上onopen方法
     */

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERID");
        log.info("websocket connection open......");
        users.put(username, session);
        log.info("欢迎用户 " + username + " 加入聊天室.....");
        log.info("当前在线用户 " + users.size() + " 人.....");
        // TODO Auto-generated method stub
        //这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户

    }

    /**
     * 关闭连接时触发
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        log.debug("websocket connection closed......");
        String username = (String) session.getAttributes().get("WEBSOCKET_USERID");
        users.remove(username);
        System.out.println("用户" + username + "已退出！");
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        session.sendMessage(new TextMessage(message.getPayload()));

    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        String username = (String) session.getAttributes().get("WEBSOCKET_USERID");
        log.debug("websocket connection closed......");
        users.remove(username);
    }

    /**
     * 群发消息
     *
     * @param msg
     * @throws IOException
     */
    public void sendMsgToAll(String msg) throws IOException {
        Set<String> key = users.keySet();
        for (String userId : key) {
            WebSocketSession user_session = users.get(userId);
            user_session.sendMessage(new TextMessage(msg));
        }
    }

    /**
     * 单发消息
     *
     * @param msg
     * @throws IOException
     */
    public void sendMsgToOne(String msg, String name) throws IOException {
        WebSocketSession user_session = users.get(name);
        user_session.sendMessage(new TextMessage(msg));
    }
}
