package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameOverviewController {

    @GetMapping("/gameOverview")
    public String gameOverview() {
        return "gameOverview"; //
    }

    @GetMapping("/gamePlantGrow")
    public String gamePlantGrow() {
        return "gamePlantGrow";
    }

    @GetMapping("/gameLove")
    public String gameLove() {
        return "gameLove";
    }

    @GetMapping("/quiz")
    public String quiz() {
        return "quiz";
    }
}
