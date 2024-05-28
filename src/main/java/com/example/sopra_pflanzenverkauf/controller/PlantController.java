package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PlantController {

    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @GetMapping("/")
    public String home(@RequestParam(value = "query", required = false) String query,
                       @RequestParam(value = "category", required = false) String category,
                       @RequestParam(value = "price", required = false) String price,
                       Model model) {
        List<Plant> plants;
        if (query != null && !query.isEmpty()) {
            plants = plantService.searchPlants(query);
        } else if (category != null || price != null) {
            plants = plantService.filterAndSortPlants(query, category, price);
        } else {
            plants = plantService.getAllPlants();
        }
        model.addAttribute("plants", plants);
        return "home";
    }
}
