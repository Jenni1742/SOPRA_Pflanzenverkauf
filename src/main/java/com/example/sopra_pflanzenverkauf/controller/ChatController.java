package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String showChat(Model model, CsrfToken csrfToken) {
        List<Message> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("_csrf", csrfToken);
        return "chat";
    }

    @PostMapping
    public String postMessage(@RequestParam("recipientId") String recipientId,
                              @RequestParam("content") String content) {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(content);
        messageDto.setRecipientId(recipientId);

        messageService.saveMessage(messageDto);
        return "redirect:/chat";
    }
}
