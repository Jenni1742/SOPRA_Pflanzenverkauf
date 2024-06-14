package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.ChatJKService;
import com.example.sopra_pflanzenverkauf.service.MessageJKService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class ChatSpecificController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;

    @RequestMapping (value = "/chatSpecific/{chatId}", method = RequestMethod.GET)
    public String showSpecificChat(@PathVariable("chatId") Integer chatId,
                                   Model model) {

        User currentUser = userService.getCurrentUser();

        ChatJK chat = chatJKService.getChatJKByChatId(chatId);

        model.addAttribute("specificChat", chat);

        System.out.println(chat.getMessagesInChat().size());


        model.addAttribute("currentUser", currentUser);

        return "chatSpecific";
    }



}
