package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.ChatJKService;
import com.example.sopra_pflanzenverkauf.service.MessageJKService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class chatConfirmSaleController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;

    /**
     * Handles GET requests targeted at the confirm sale page.
     *
     * @return  the confirm sale page
     */
    @RequestMapping(value = "/chatConfirmSale", method = RequestMethod.GET)
    public String showChatConfirmSale(@RequestParam(value = "chatId", required = false) String chatId,
                                          Model model) {

        ChatJK currentChat = null;

        for (ChatJK chat : chatJKService.getAllChats()) {
            if (chat.getChatId().toString().equals(chatId)) {
                currentChat = chat;
            }
        }

        model.addAttribute("chat", currentChat);

        model.addAttribute("currentUser", userService.getCurrentUser());

        return "chatConfirmSale";
    }

    @RequestMapping (value = "/chatConfirmSale/{chatId}", method = RequestMethod.GET)
    public String showSale(@PathVariable("chatId") Integer chatId,
                                   Model model) {

        User currentUser = userService.getCurrentUser();

        ChatJK chat = chatJKService.getChatJKByChatId(chatId);

        model.addAttribute("specificChat", chat);

        model.addAttribute("chatId", chatId);

        model.addAttribute("chatPlant", chat.getChatPlant());

        System.out.println(chat.getMessagesInChat().size());

        model.addAttribute("currentUser", currentUser);

        return "chatConfirmSale";
    }
}
