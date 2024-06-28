package com.example.sopra_pflanzenverkauf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorIDDoNotExistController {

    @RequestMapping(value = "/errorIDDoNotExist", method = RequestMethod.GET)
    public String showErrorIDDoNotExistPage(Model model) {

        return "error/errorIDDoNotExist";
    }
}
