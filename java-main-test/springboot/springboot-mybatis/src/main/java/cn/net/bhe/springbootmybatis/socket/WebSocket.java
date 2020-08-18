package cn.net.bhe.springbootmybatis.socket;

import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.PostConstruct;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/websocket/test")
@Component
public class WebSocket {
    static final Logger LOGGER = LoggerFactory.getLogger(WebSocket.class);
    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<WebSocket>();
    
    private Session session; // 每个连接一个会话
    
    @PostConstruct
    public void init() {
        LOGGER.info(this.toString());
    }

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSockets.add(this); // 每个连接一个this实例
        broadcast(session.getId() + "加入");
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
    }

    @OnMessage
    public void onMessage(String message) {
        LOGGER.info(message);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        LOGGER.error("", error);
    }

    public void broadcast(String message) {
        try {
            for (WebSocket webSocket : webSockets) {
                webSocket.session.getBasicRemote().sendText(message);
            }
        } catch (Exception e) {
            LOGGER.error("", e);
        }
    }
    
}