package com.again.cloud.socket.handlers;

import com.again.cloud.socket.lserver.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ChatMessageHandler extends TextWebSocketHandler {
    private final UserRepository userRepository;

    /**
     * 当和用户成功建立连接的时候会调用此方法,在此方法内部应该保存连接
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("create connection success");
        String name = (String) session.getAttributes().get("username");// 将在拦截器中保存的用户的名字取出来,然后作为
        // key 存到 map 中
        if (name != null) {
            userRepository.put(name, session);// 保存当前的连接和用户之间的关系
        }
        // 这块会实现自己业务，比如，当用户登录后，会把离线消息推送给用户
    }

    /**
     * 收到消息的时候会触发该方法
     * @param session 发送消息的用户的 session
     * @param message 发送的内容
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        InetSocketAddress address = session.getLocalAddress();
        System.out.println(address);
        System.out.println(message.toString());
    }

    /**
     * 给某个用户发送消息
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        WebSocketSession webSocketSession = userRepository.get(userName);// 根据接收方的名字找到对应的连接
        if (webSocketSession != null && webSocketSession.isOpen()) {// 如果没有离线,如果离线,请根据实际业务需求来处理,可能会需要保存离线消息
            try {
                webSocketSession.sendMessage(message);// 发送消息
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给所有在线用户发送消息,此处以文本消息为例子
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (Map.Entry<String, WebSocketSession> webSocketSessionEntry : userRepository.getAll().entrySet()) {// 获取所有的连接
            WebSocketSession session = webSocketSessionEntry.getValue();// 找到每个连接
            if (session != null && session.isOpen()) {
                try {
                    session.sendMessage(message);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 出现异常的时候
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        String name = (String) session.getAttributes().get("username");
        if (session.isOpen()) {
            session.close();
        }
        try {
            userRepository.remove(name);// 移除连接
        }
        catch (Exception e) {
            log.error("handleTransportError error:{}", e.getMessage(), e);
        }
    }

    /**
     * 连接关闭后
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String name = (String) session.getAttributes().get("oid");// 找到用户对应的连接
        try {
            userRepository.remove(name);// 移除
        }
        catch (Exception e) {
            log.error("handleTransportError error:{}", e.getMessage(), e);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
