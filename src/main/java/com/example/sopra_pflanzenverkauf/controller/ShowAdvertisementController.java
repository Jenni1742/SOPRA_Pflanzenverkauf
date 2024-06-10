package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ShowAdvertisementController {
    @Autowired
    private PlantService plantService;

    @Autowired
    private PlantRepository plantRepository;

    /**
     * Handles GET requests targeted at the showAdvertisement page.
     *
     * @return  the showAdvertisement page
     */
    @GetMapping ("/showAdvertisement/{id}")
    public String showPlant(@PathVariable("id") Integer plantId, Model model) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
                });
        model.addAttribute("plant", plant);
        return "showAdvertisement";
    }
}
