package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private PlantService plantService;

    @GetMapping("/search")
    public String search(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            List<Plant> plants = plantService.searchPlants(query);
            model.addAttribute("plants", plants);
            model.addAttribute("query", query);
            if (plants.isEmpty()) {
                model.addAttribute("message", "Keine Pflanzen gefunden. Versuchen Sie es mit anderen Suchbegriffen.");
            }
        }
        return "search_results";
    }
}
