package com.again.cloud.socket.lserver;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("ALL")
@Component
@Service
@RequiredArgsConstructor
public class UserRepository {

    private final static Map<String, WebSocketSession> SOCKET_SESSION_MAP = new ConcurrentHashMap<>();

    public void put(String key, WebSocketSession value) {
        SOCKET_SESSION_MAP.put(key, value);
    }

    public WebSocketSession get(String key) {
        return SOCKET_SESSION_MAP.get(key);
    }

    public Map<String, WebSocketSession> getAll() {
        return SOCKET_SESSION_MAP;
    }

    public void remove(String key) {
        SOCKET_SESSION_MAP.remove(key);
    }

    public int size() {
        return SOCKET_SESSION_MAP.size();
    }

}
