package com.example.sopra_pflanzenverkauf.controller;

import ch.qos.logback.core.model.Model;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@Controller
public class PlantDetailsController {
    @Autowired
    private PlantService plantService;

    @GetMapping("/plant-details/{id}")
    public String getPlantDetails(@PathVariable("id") Integer id, Model model) {
        Plant plant = plantService.findById(id); // Pflanzendetails abrufen
        if (plant != null) {
            model.addText("plant");
            return "plant-detail";
        } else {
            return "error/404"; // Optional: Fehlerseite bei nicht gefundenem Plant
        }
    }
}

