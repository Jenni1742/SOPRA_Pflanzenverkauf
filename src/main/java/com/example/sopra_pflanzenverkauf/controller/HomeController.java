package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private PlantService plantService;

    @GetMapping("/")
    public String showHome(Model model,
                           @RequestParam(value = "query", required = false) String query,
                           @RequestParam(value = "category", required = false) Category category,
                           @RequestParam(value = "price", required = false) String price)  {

        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (query != null && !query.isEmpty()) {
            //List<Plant> plants = plantService.searchPlantsByName(query);

            List<Plant> plants = new ArrayList<>();
            for (User user:userService.findAllUsers()) {
                if (user != currentUser) {
                    plants.addAll(user.getPlantsToSell());
                }
            }

            model.addAttribute("plants", plants);
            return "searchresults";  // Leitet zur Suchergebnisseite weiter, wenn eine Suchanfrage vorhanden ist
        }

        List<Plant> plants = plantService.findFilteredAndSortedPlants(category, price);

        /*
        List<Plant> plants = new ArrayList<>();
        for (User user:userService.findAllUsers()) {
            if (user != currentUser) {
                System.out.println(user.getPlantsToSell().size());
                plants.addAll(user.getPlantsToSell());
            }
        }
        */

        model.addAttribute("plants", plants);

        return "home";
    }
    @GetMapping("/plants")
    public String getPlants(Model model) {
        // Filtern der Pflanzen nach sold = false
        List<Plant> plants = plantService.getAllPlants().stream()
                .filter(plant -> !plant.getSold())
                .collect(Collectors.toList());
        model.addAttribute("plants", plants);
        return "home"; // Name der HTML-Datei, die die Pflanzenliste anzeigt
    }

    @PostMapping(path = "/")
    public String addToWishlist(@RequestParam("plant") Integer plant,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        if (currentUser != null && plant != null) {
            currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plant));

            userService.updateWishlist(currentUser);
        }

        model.put("currentUser", currentUser);
        return "redirect:/";
    }

    @PostMapping(path = "/delete")
    public String removePlantFromWishlist(@RequestParam("plant") Integer plant,
                                          Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        currentUser.getWishlistPlants().remove(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);


        model.put("currentUser", currentUser);
        return "redirect:/";
    }
}