package cs.blokus.websocket.security.configuration;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class StompHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest req, ServerHttpResponse resp, WebSocketHandler h, Map<String, Object> atts) {
    	 System.out.println("handsjakr" );
        try {
           /// String token = protocols.get(0);
          
           // resp.setStatusCode(HttpStatus.SWITCHING_PROTOCOLS);
           
        } catch (IndexOutOfBoundsException e) {
            resp.setStatusCode(HttpStatus.UNAUTHORIZED);
            return false;
        }
  
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest rq, ServerHttpResponse rp, WebSocketHandler h, @Nullable Exception e) {}
}
