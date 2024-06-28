package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorEditAdvertisementController {

    @RequestMapping(value = "/errorEditAdvertisement", method = RequestMethod.GET)
    public String showErrorEditAdvertisementPage(Model model) {

        return "error/errorEditAdvertisement";
    }
}
