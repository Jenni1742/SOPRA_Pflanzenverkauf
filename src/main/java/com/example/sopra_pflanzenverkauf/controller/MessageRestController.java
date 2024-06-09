package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

    private final MessageService messageService;

    @Autowired
    public MessageRestController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public Message createMessage(@RequestBody MessageDto messageDto) {
        return messageService.saveMessage(messageDto);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }
}
