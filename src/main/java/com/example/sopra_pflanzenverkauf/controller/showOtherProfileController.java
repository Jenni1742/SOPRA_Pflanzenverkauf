package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class showOtherProfileController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/showOtherProfile", method = RequestMethod.GET)
    public String showOtherUserprofilePage(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "showOtherProfile";
    }
}
