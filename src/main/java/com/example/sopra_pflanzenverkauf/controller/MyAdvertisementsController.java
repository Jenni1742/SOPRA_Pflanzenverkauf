package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MyAdvertisementsController {

    @Autowired
    private PlantService plantService;

    /**
     * Handles GET requests targeted at the advertisements page.
     *
     * @return  the advertisements page
     */
    @RequestMapping(value = "/myAdvertisements", method = RequestMethod.GET)
    public String showMyAdvertisementsPage(Model model) {
        return "myAdvertisements";
    }
}
