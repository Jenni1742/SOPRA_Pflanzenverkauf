package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/searchresults")
    public String search(@RequestParam("query") String query, Model model) {
        List<Plant> results = plantService.searchPlantsByName(query);
        model.addAttribute("query", query);
        model.addAttribute("plants", results);
        System.out.println("Search query received: " + query);  // Logging für Debugging
        return "searchresults";
    }
}
