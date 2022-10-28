package com.powerrangers.linkedhu.controller;

import com.powerrangers.linkedhu.dto.PrivateMessageDTO;
import com.powerrangers.linkedhu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;

@CrossOrigin
@Controller
@RequiredArgsConstructor
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private final  UserService userService;

    @MessageMapping("/chat")
    public ResponseEntity<String> chatEndpoint(@Payload PrivateMessageDTO messageDTO) {
        ResponseEntity<String> response = userService.sendPrivateMessage(messageDTO);
        //database
        if (response.hasBody() && !response.getBody().contains("Error")) {
            messagingTemplate.convertAndSend("/topic", messageDTO);
            //messagingTemplate.convertAndSend(messageDTO.getReceiverUsername(),"/topic" );
//            messagingTemplate.convertAndSendToUser(messageDTO.getReceiverUsername(), "/topic", messageDTO);

            //messagingTemplate.convertAndSendToUser(messageDTO.getSenderUsername(), "/topic", messageDTO);
        }
        System.out.println(response);
        return response;
    }
}
