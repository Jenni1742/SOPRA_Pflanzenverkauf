package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.ChatJK;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.ChatJKService;
import com.example.sopra_pflanzenverkauf.service.MessageJKService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ChatJKController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageJKService messageJKService;

    @Autowired
    private ChatJKService chatJKService;


    /**
     * Request Mapping after a successful login.
     *
     * @param model the model.
     * @return overview-page.
     */
    @RequestMapping(value = "/chatJK", method = RequestMethod.GET)
    public String showChatJKPage(Model model) {

        User currentUser = userService.getCurrentUser();

        List<ChatJK> chatList = currentUser.getReceivedChat();
        chatList.addAll(currentUser.getSentChat());

        System.out.println(chatList.size());

        model.addAttribute("chatList", chatList);

        model.addAttribute("currentUser", currentUser);

        return "chatJK";
    }

    @GetMapping("/showSpecificChat")
    public String showHome(Model model) {

        User currentUser = userService.getCurrentUser();

        model.addAttribute("currentUser", currentUser);

        return "chatJK";
    }

}