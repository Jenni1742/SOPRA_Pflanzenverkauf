package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class CreateAdvertisementController {

    @Autowired
    private PlantService plantService;

    /**
     * Handles GET requests targeted at the createAdvertisement page.
     *
     * @return  the createAdvertisement page
     */
    @RequestMapping(value = "/createAdvertisement", method = RequestMethod.GET)
    public String showCreateAdvertisementsPage(Model model) {
        model.addAttribute("plant", new Plant());
        return "createAdvertisement";
    }


    @RequestMapping (value="/createAdvertisement", method = RequestMethod.POST)
    public String savePlant(@ModelAttribute("plant") Plant plant){
        plantService.persistPlant(plant);
        return "myAdvertisements";
    }



}
