package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Chat;
import com.example.sopra_pflanzenverkauf.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ErrorDeleteAdvertisement {

    @RequestMapping(value = "/errorDeleteAdvertisement", method = RequestMethod.GET)
    public String showErrorDeleteAdvertisementPage(Model model) {

        return "error/errorDeleteAdvertisement";
    }
}
