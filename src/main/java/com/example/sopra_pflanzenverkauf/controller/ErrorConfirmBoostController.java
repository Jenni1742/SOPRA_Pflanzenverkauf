package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorConfirmBoostController {
    @RequestMapping(value = "/errorConfirmBoost", method = RequestMethod.GET)
    public String showErrorConfirmBoostPage(Model model) {

        return "error/errorConfirmBoost";
    }
}
