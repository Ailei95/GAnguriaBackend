package xyz.ganguria.socket.support;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import xyz.ganguria.socket.controller.WebSocketBrokerController;
import xyz.ganguria.socket.security.StompPrincipal;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    private WebSocketBrokerController webSocketBrokerController;

    // Custom class for storing principal
    @Override
    protected Principal determineUser(
            ServerHttpRequest request,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes
    ) {
        // Generate principal with UUID as name

        return new StompPrincipal(UUID.randomUUID().toString());
    }
}
