package com.example.sopra_pflanzenverkauf.controller;

import ch.qos.logback.core.model.Model;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


@Controller
public class PlantDetailsController {
    @Autowired
    private PlantService plantService;

    @GetMapping("/plant-details/{id}")
    public String getPlantDetails(@PathVariable("id") Integer id, Map<String, Object> model) {
        System.out.println("Received request for plant ID: " + id); // Debugging-Ausgabe

        Plant plant = plantService.findById(id); // Pflanzendetails abrufen
        if (plant != null) {
            System.out.println("Plant found: " + plant); // Debugging-Ausgabe
            model.put("plant", plant);
            return "plant-detail";
        } else {
            System.out.println("No plant found with ID: " + id); // Debugging-Ausgabe
            return "error/404"; // Optional: Fehlerseite bei nicht gefundenem Plant
        }
    }
}

