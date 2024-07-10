package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import jakarta.servlet.http.HttpSession;
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
    public String search(@RequestParam("query") String query,
                         @RequestParam(name = "priceMin", required = false) Integer priceMin,
                         @RequestParam(name = "priceMax", required = false) Integer priceMax,
                         @RequestParam(name = "sizeMin", required = false) Integer sizeMin,
                         @RequestParam(name = "sizeMax", required = false) Integer sizeMax,
                         HttpSession session,
                         Model model) {
        List<Plant> results = plantService.searchPlantsByName(query);

        System.out.println("A");
        System.out.println(results.size());
        for (Plant plant : results) {
            System.out.println(plant.getPlantname());
        }

        User currentUser = userService.getCurrentUser();

        int i = 0;
        while (i < results.size()) {
            Plant plant = results.get(i);
            if (plant.getSeller() == currentUser || plant.getSold()) {
                System.out.println("B");
                results.remove(plant);
                System.out.println("B");
                i = i;
            } else {
                i = i + 1;
            }
            System.out.println(i);
            System.out.println(results.size());
        }
        session.setAttribute("searchResults", results);  // Save search results in session
        session.setAttribute("query", query);  // Save query in session

        model.addAttribute("query", query);
        model.addAttribute("plants", results);
        System.out.println("Search query received: " + query);  // Logging für Debugging

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("priceMin", 0);
        model.addAttribute("priceMax", 100);
        model.addAttribute("sizeMin", 1);
        model.addAttribute("sizeMax", 100);
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
