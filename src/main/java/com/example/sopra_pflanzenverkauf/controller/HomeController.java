package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private PlantService plantService;

    @GetMapping("/")
    public String showHome(Model model, @RequestParam(value = "query", required = false) String query,
                           @RequestParam(value = "category", required = false) String category,
                           @RequestParam(value = "price", required = false) String price)  {
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (query != null && !query.isEmpty()) {
            List<Plant> plants = plantService.searchPlantsByName(query);
            model.addAttribute("plants", plants);
            return "searchresults";  // Leitet zur Suchergebnisseite weiter, wenn eine Suchanfrage vorhanden ist
        }

        List<Plant> plants = plantService.findFilteredAndSortedPlants(category, price);
        model.addAttribute("plants", plants);

        return "home";
    }
}
