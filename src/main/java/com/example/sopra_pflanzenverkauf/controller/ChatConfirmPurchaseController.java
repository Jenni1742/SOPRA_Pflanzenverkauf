package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.ChatJKService;
import com.example.sopra_pflanzenverkauf.service.MessageJKService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ChatConfirmPurchaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;

    /**
     * Handles GET requests targeted at the confirm purchase page.
     *
     * @return  the confirm purchase page
     */
    @RequestMapping(value = "/chatConfirmPurchase", method = RequestMethod.GET)
    public String showChatConfirmPurchase(Model model) {

        return "chatConfirmPurchase";
    }

}
