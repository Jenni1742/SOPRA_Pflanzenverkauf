package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class CreateAdvertisementController {

    /**
     * Handles GET requests targeted at the createAdvertisement page.
     *
     * @return  the createAdvertisement page
     */
    @RequestMapping(value = "/createAdvertisement", method = RequestMethod.GET)
    public String showMyAdvertisementsPage(Map<String, Object> model) {
        return "createAdvertisement";
    }
}
