package Socket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;


@Service
public class SocketHandler implements WebSocketHandler {

    /*private final static Logger log=Logger.getLogger(SocketHandler.class);*/
    private static Map<String,WebSocketSession> userMap;
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        System.out.println("成功进入了系统。。。");
    }
    /**
     * 发送信息时， 调用此方法传输数据
     */
@Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {

    }
@Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }
@Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }
@Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
