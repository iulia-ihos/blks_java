package cs.blokus.websocket.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import cs.blokus.websocket.security.configuration.StompHandshakeInterceptor;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
 
	@Autowired
	private StompHandshakeInterceptor interceptor; 
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/game");
        config.setApplicationDestinationPrefixes("/app");
    }
 
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       //  registry.addEndpoint("/stomp-endpoint").setAllowedOrigins("*");
         registry.addEndpoint("/stomp-endpoint")
        // .addInterceptors((interceptor))
         .setAllowedOrigins("http://localhost:4200")
        
         .withSockJS();
    }
}