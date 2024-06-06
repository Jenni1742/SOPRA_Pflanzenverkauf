package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShowAdvertisementController {
    @Autowired
    private PlantService plantService;

    /**
     * Handles GET requests targeted at the showAdvertisement page.
     *
     * @return  the showAdvertisement page
     */
    @RequestMapping(value = "/showAdvertisement", method = RequestMethod.GET)
    public String showAdvertisementPage(Model model) {

        return "showAdvertisement";
    }
}
