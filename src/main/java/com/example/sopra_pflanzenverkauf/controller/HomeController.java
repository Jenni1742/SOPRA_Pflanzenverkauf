package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.CategoryService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import jakarta.servlet.http.HttpSession;
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
                           @RequestParam(value = "price", required = false) String price,
                           @RequestParam(name = "planter", required = false) String planter,
                           @RequestParam(name = "priceMin", required = false) Integer priceMin,
                           @RequestParam(name = "priceMax", required = false) Integer priceMax,
                           @RequestParam(name = "sizeMin", required = false) Integer sizeMin,
                           @RequestParam(name = "sizeMax", required = false) Integer sizeMax,
                           @RequestParam(name = "sort", required = false) String sort)  {

        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (query != null && !query.isEmpty()) {
            List<Plant> plants = plantService.searchPlantsByName(query);
            model.addAttribute("plants", plants);
            return "searchresults";  // Leitet zur Suchergebnisseite weiter, wenn eine Suchanfrage vorhanden ist
        }

        List<Plant> plants = plantService.getFilteredAndSortedPlants(category, planter, priceMin, priceMax, sizeMin, sizeMax,  null, false);
        if (plants == null) {
            plants = new ArrayList<>();
        }

        List<Plant> boostedPlants = plantService.getFilteredAndSortedPlants(category, planter, priceMin, priceMax, sizeMin, sizeMax, null, false);

        int i = 0;
        while (i < plants.size()) {
            Plant plant = plants.get(i);
            if (plant.getSeller() == currentUser || plant.getSold()) {
                plants.remove(i);
            } else {
                i++;
            }
        }

        model.addAttribute("plants", plants);

        return "home";
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
        return "home";
    }

    @PostMapping(path = "/")
    public String addToWishlist(@RequestParam("plant") Integer plant,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plant).getPlantname());

        if (currentUser != null && plant != null) {
            currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plant));

            userService.updateWishlist(currentUser);
        }

        model.put("currentUser", currentUser);
        Plant plantObject = plantService.getPlantByPlantId(plant);
        if (plantObject.getBooster() == true) {
            return "redirect:/" + "#B" + plant;
        } else {
            return "redirect:/" + "#" + plant;
        }


    }

    @PostMapping(path = "/delete")
    public String removePlantFromWishlist(@RequestParam("plant") Integer plant,
                                          Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plant).getPlantname());

        currentUser.getWishlistPlants().remove(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);


        model.put("currentUser", currentUser);
        Plant plantObject = plantService.getPlantByPlantId(plant);
        if (plantObject.getBooster() == true) {
            return "redirect:/" + "#B" + plant;
        } else {
            return "redirect:/" + "#" + plant;
        }
    }
}