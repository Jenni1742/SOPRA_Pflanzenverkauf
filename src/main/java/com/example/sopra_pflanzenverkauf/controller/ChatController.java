package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageService messageService;
    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String showChat(Map<String, Object> model) {
        return "chat";
    }

    @PostMapping
    public String postMessage(
            @RequestParam("recipientId") String recipientId,
            @RequestParam("content") String content,
            Map<String, Object> model
    ) {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(content);
        messageDto.setRecipientId(recipientId);

       Message message = messageService.saveMessage(messageDto);
       return "chat";
    }

}


