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
    public String showChatConfirmSale(Model model) {

        return "chatConfirmSale";
    }
}
