package xyz.ganguria.socket.config;

import org.springframework.context.annotation.Configuration;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import xyz.ganguria.socket.support.CustomHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/api/socket")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .setAllowedOriginPatterns("*")
                .withSockJS();
        registry.addEndpoint("/api/socket")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/api")
                .setUserDestinationPrefix("/user")
                .enableSimpleBroker("/queue/reply", "/api/message", "/api/users");

    }
/*
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration){
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);

                if(StompCommand.CONNECT.equals(accessor.getCommand())){
                    System.out.println("Connect ");
                } else if(StompCommand.SUBSCRIBE.equals(accessor.getCommand())){
                    System.out.println("Subscribe ");
                } else if(StompCommand.SEND.equals(accessor.getCommand())){
                    System.out.println("Send message " );
                } else if(StompCommand.DISCONNECT.equals(accessor.getCommand())){
                    System.out.println("Exit ");
                } else {
                }
                return message;
            }
        });
    }
 */
}
