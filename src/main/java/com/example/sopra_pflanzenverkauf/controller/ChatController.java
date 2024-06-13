package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private final MessageService messageService;
    private final UserService userService;

    @Autowired
    public ChatController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @GetMapping
    public String getChatPage(
            @RequestParam(value = "recipientId", required = false) String recipientId, Model model) {
        User currentUser = userService.getCurrentUser();
        model.addAttribute("recipientId", recipientId);
        model.addAttribute("currentUser", currentUser);
        return "chat";
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, String>> postMessage(@RequestBody MessageDto messageDto) {
        messageService.saveMessage(messageDto);
        // Rückgabe einer JSON-Antwort
        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
