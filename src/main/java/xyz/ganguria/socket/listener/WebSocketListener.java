package xyz.ganguria.socket.listener;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class WebSocketListener {

    private SimpMessagingTemplate template;
    private SimpUserRegistry simpUserRegistry;

    public WebSocketListener(
            SimpMessagingTemplate messagingTemplate,
            SimpUserRegistry simpUserRegistry
    ) {
        this.template = messagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
    }

    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        updateListUsers();
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        updateListUsers();
    }

    @EventListener
    public void handleSessionSubscribeEvent(SessionSubscribeEvent event) {
        GenericMessage message = (GenericMessage) event.getMessage();
        String simpDestination = (String) message.getHeaders().get("simpDestination");

        if (simpDestination.startsWith("/api/users")) {
            updateListUsers();
        }
    }

    private void updateListUsers() {
        Set<SimpUser> users = simpUserRegistry.getUsers();
        List<String> username = new LinkedList<>();

        for (SimpUser user : users) { username.add(user.getName()); }

        template.convertAndSend("/api/users", username);
    }
}
