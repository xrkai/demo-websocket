package com.example.websocket.config.websocket;

import com.example.websocket.interceptor.WebSocketInterceptor;
import com.example.websocket.socket.EchoHander;
import com.example.websocket.socket.EchoSocketHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket配置类
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private EchoHander echoHander;

    @Autowired
    private EchoSocketHander echoSocketHander;

    @Autowired
    private WebSocketInterceptor webSocketInterceptor;

    /**
     * 注册websocket
     *
     * @param webSocketHandlerRegistry
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        // setAllowedOrigins 跨域解决
        webSocketHandlerRegistry.addHandler(echoHander, "/echoHandler/{ID}").addInterceptors(webSocketInterceptor).setAllowedOrigins("*");
        //  withSockJS http协议
        webSocketHandlerRegistry.addHandler(echoSocketHander, "/echoSocketHander/{ID}").addInterceptors(webSocketInterceptor).setAllowedOrigins("*").withSockJS();
    }


}
