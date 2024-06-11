package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameLoveController {
    @Autowired
    private UserService userService;
    @Autowired
    private PlantService plantService;
    @GetMapping("/gameLove")
    public String gameLove() {
        return "gameLove";
    }
}
