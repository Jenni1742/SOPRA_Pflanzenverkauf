package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.CategoryService;
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
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/")
    public String showHome(Model model,
                           @RequestParam(value = "query", required = false) String query,
                           @RequestParam(value = "category", required = false) Category category,
                           @RequestParam(value = "price", required = false) String price)  {

        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (query != null && !query.isEmpty()) {
            List<Plant> plants = plantService.searchPlantsByName(query);

            for (Plant plant : plants) {
                if (plant.getSeller() == currentUser) {
                    plants.remove(plant);
                } else if (plant.getSold() == true) {
                    plants.remove(plant);
                }
            }

            model.addAttribute("plants", plants);
            return "searchresults";  // Leitet zur Suchergebnisseite weiter, wenn eine Suchanfrage vorhanden ist
        }

        List<Plant> plants = plantService.findFilteredAndSortedPlants(category, price, false);

        model.addAttribute("plants", plants);

        return "home";
    }
    @GetMapping("/filteredPlants")
    public String showFilteredPlants(Model model,
                                     @RequestParam(value = "category", required = false) String categoryName,
                                     @RequestParam(value = "status", required = false) Boolean sold) {

        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        /**List<Plant> plants = plantService.getAllPlants().stream()
                .filter(plant -> category == null || plant.getCategory().getCategoryname().equalsIgnoreCase(category))
                .filter(plant -> sold == null || plant.getSold() == sold)
                .filter(plant -> plant.getSeller() != currentUser)
                .collect(Collectors.toList());
        */
        Category category = null;
        if (categoryName != null && !categoryName.isEmpty()) {
            category = categoryService.getCategoryByName(categoryName);
        }
        List<Plant> plants = plantService.findFilteredAndSortedPlants(category, null, sold);
        plants = plants.stream()
                .filter(plant -> plant.getSeller() != null && !plant.getSeller().equals(currentUser))
                .collect(Collectors.toList());

        model.addAttribute("plants", plants);

        return "filteredPlants"; // Name der HTML-Datei, die die gefilterten Pflanzen anzeigt
    }
    @GetMapping("/plants")
    public String getPlants(Model model) {

        // Filtern der Pflanzen nach Verkäufer != currentUser
        User currentUser = userService.getCurrentUser();
        // Filtern der Pflanzen nach sold = false
        List<Plant> plants = plantService.getAllPlants().stream()
                .filter(plant -> !plant.getSold())
                .filter(plant -> !(plant.getSeller() == currentUser))
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