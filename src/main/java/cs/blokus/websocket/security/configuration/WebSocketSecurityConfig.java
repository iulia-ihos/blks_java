package cs.blokus.websocket.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.messaging.MessageSecurityMetadataSourceRegistry;
import org.springframework.security.config.annotation.web.socket.AbstractSecurityWebSocketMessageBrokerConfigurer;

@Configuration
public class WebSocketSecurityConfig extends AbstractSecurityWebSocketMessageBrokerConfigurer {
	
	
	
    @Override
    protected void configureInbound(MessageSecurityMetadataSourceRegistry messages) {
        messages
          .simpSubscribeDestMatchers("/game/**").authenticated()
          .simpDestMatchers("/stomp-endpoint/**").authenticated()
          .anyMessage().authenticated();
    }   
    
    @Override
    protected boolean sameOriginDisabled() {
      return true;
    }
}