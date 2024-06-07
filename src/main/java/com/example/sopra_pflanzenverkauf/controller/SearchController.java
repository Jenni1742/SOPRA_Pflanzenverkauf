package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

@Controller
public class SearchController {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @GetMapping("/searchresults")
    public String search(@RequestParam("query") String query, Model model) {
        List<Plant> results = plantService.searchPlantsByName(query);
        model.addAttribute("query", query);
        model.addAttribute("plants", results);
        System.out.println("Search query received: " + query);  // Logging für Debugging

        User currentUser = userService.getCurrentUser();
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "searchresults";
    }

    @PostMapping(path = "/searchresults")
    public String addToWishlist(@RequestParam("plant") Integer plant,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);

        model.put("currentUser", currentUser);
        return "redirect:/searchresults";
    }

    @PostMapping(path = "/searchresults/delete")
    public String removePlantFromWishlist(@RequestParam("plant") Integer plant,
                                          Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        currentUser.getWishlistPlants().remove(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);


        model.put("currentUser", currentUser);
        return "redirect:/searchresults";
    }
}
