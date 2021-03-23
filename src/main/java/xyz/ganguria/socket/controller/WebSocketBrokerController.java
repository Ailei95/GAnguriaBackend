package xyz.ganguria.socket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import org.springframework.stereotype.Controller;
import xyz.ganguria.socket.model.Message;

import java.security.Principal;
import java.sql.Timestamp;


@Controller
public class WebSocketBrokerController {

    private final SimpMessagingTemplate template;

    @Autowired
    WebSocketBrokerController(SimpMessagingTemplate template){
        this.template = template;
    }

    @MessageMapping("/secured/room")
    public void sendMessage(
            @Payload Message msg,
            Principal user,
            @Header("simpSessionId") String sessionId) throws Exception {
        template.convertAndSendToUser(msg.getTo(), "/queue/reply", msg.getText());
    }

    @MessageMapping("/send/message")
    @SendTo("/api/message")
    public Message sendMessage(Message message){
        Message msg = message;
        msg.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        return msg;
    }
}
