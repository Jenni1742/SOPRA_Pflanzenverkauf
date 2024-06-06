package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * Handles GET requests targeted at the register page.
     *
     * @return  the register page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage() {
        return "register";
    }
}
