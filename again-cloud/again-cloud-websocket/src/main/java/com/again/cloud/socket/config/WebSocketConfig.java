package com.again.cloud.socket.config;

import com.again.cloud.socket.handlers.ChatMessageHandler;
import com.again.cloud.socket.inteceptors.ChatHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration //声明为配置文件
@EnableWebSocket//启用 websocket
@RequiredArgsConstructor
@Slf4j
public class WebSocketConfig implements WebSocketConfigurer {

    private final ChatMessageHandler chatMessageHandler;

    private final ChatHandshakeInterceptor chatHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        System.out.println("初始化路径拦截");// 指定所有/websocket开头的路径会被 websocket 拦截,设置处理器和拦截器
        webSocketHandlerRegistry.addHandler(chatMessageHandler, "/websocket/*")
                .addInterceptors(chatHandshakeInterceptor).setAllowedOrigins("*");
    }

}
