package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class ChatController {

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String showChat(Map<String, Object> model) {
        return "chat";
    }
}


