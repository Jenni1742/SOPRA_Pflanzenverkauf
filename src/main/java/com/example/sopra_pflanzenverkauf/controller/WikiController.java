package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WikiController {
    @GetMapping("/wiki")
    public String showWiki() {
        return "wiki";
    }
}
