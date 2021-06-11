package com.again.cloud.socket.inteceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Component
@Slf4j
public class ChatHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 在握手之前执行该方法, 继续握手返回true, 中断握手返回false. 通过attributes参数设置WebSocketSession的属性
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        // 为了方便区分来源以及在后面的时候方便找到会话主动向客户端发送消息,我们需要区分每个会话并保存,在此以用户的名字来区分,名字我们通过要求用输入进行传递,所以在这里先从请求中获取到用户输入的名字,因为是使用的rest
        // 风格,所以规定路径的最后一个字符串是名字
        System.out.println("握手之前");
        String s = request.getURI().toString();
        String s1 = s.substring(s.lastIndexOf("/") + 1);
        attributes.put("username", s1);// 给当前连接设置属性
        System.out.println(s);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    /**
     * 在握手之后执行该方法. 无论是否握手成功都指明了响应状态码和相应头.
     * @param request
     * @param response
     * @param wsHandler
     * @param ex
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        System.out.println("After Handshake");
        super.afterHandshake(request, response, wsHandler, ex);
    }

}
