package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.ChatService;
import com.example.sopra_pflanzenverkauf.service.MessageService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class ChatController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;


    /**
     * Request Mapping after a successful login.
     *
     * @param model the model.
     * @return overview-page.
     */
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String showChatPage(Model model) {

        User currentUser = userService.getCurrentUser();

        List<Chat> chatList = currentUser.getReceivedChat();
        chatList.addAll(currentUser.getSentChat());

        model.addAttribute("chatList", chatList);

        model.addAttribute("currentUser", currentUser);

        return "chat";
    }

    /*
    @PostMapping("/chat/{chatId}")
    public String showSpecificChat(@PathVariable("chatId") Integer chatId,
                           Model model) {

        User currentUser = userService.getCurrentUser();

        Chat chat = chatService.getChatByChatId(chatId);

        model.addAttribute("specificChat", chat);


        model.addAttribute("currentUser", currentUser);

        return "chat";
    }*/

}