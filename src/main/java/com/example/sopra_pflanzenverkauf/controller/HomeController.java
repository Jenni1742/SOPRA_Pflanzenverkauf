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
                           @RequestParam(value = "price", required = false) String price,
                           @RequestParam(value = "sort", required = false) String sort) {

        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", currentUser);

        List<Plant> plants;

        if (query != null && !query.isEmpty()) {
            plants = plantService.searchPlantsByName(query);
            model.addAttribute("plants", plants);
            return "searchresults";
        }

        plants = plantService.getAllPlants(sort);
        model.addAttribute("plants", plants);
        return "home";
    }

    @PostMapping(path = "/")
    public String addToWishlist(@RequestParam("plant") Integer plant,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);

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
