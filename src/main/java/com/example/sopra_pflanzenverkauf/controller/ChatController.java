package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.dto.MessageDto;
import com.example.sopra_pflanzenverkauf.entity.Message;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
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
    private PlantService plantService;
    private UserService userService;


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
    @RequestMapping("/chat/{sellerName}/{plantId}")
    public String chat(@PathVariable("sellerName") String sellerName, @PathVariable("plantId") Integer plantId, Model model) {

        User seller = userService.getUserByUsername(sellerName);
        Plant plant = plantService.findById(plantId);


        model.addAttribute("seller", seller);
        model.addAttribute("plant", plant);

        return "chat";
    }
}
