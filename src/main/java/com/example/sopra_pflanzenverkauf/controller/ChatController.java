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
    public String showChat(Model model) {
        List<Message> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);

        // Add CSRF token
        CsrfToken csrfToken = (CsrfToken) model.asMap().get("_csrf");
        model.addAttribute("_csrf", csrfToken);

        return "chat";
    }


    @PostMapping
    public String postMessage(@RequestParam("recipientId") String recipientId,
                              @RequestParam("content") String content,
                              Model model) {
        MessageDto messageDto = new MessageDto();
        messageDto.setContent(content);
        messageDto.setRecipientId(recipientId);

        Message message = messageService.saveMessage(messageDto);
        model.addAttribute("message", message);
        return "redirect:/chat";
    }
}
