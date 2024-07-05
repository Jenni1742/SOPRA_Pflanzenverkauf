package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class PlantDetailOtherProfileController {
    @Autowired
    private PlantService plantService;
    @Autowired
    private PlantRepository plantRepository;
    @Autowired
    private UserService userService;

    /**
     @GetMapping("/plant-detail/{id}")
     public String getPlantDetails(@PathVariable("id") Integer id, Map<String, Object> model) {
     Plant plant = plantService.findById(id);
     if (plant != null) {
     model.put("plant", plant);
     return "plant-detail";
     } else {
     return "error/404";
     }
     }
     */

    @GetMapping("/plantDetailSeller/{id}")
    public String showPlantDetailWishlist(@PathVariable("id") Integer plantId, Model model) {

        User currentUser = userService.getCurrentUser();

        Plant plant = plantRepository.findById(plantId)
                .orElse(null);
        model.addAttribute("plant", plant);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("sellerId", plant.getSeller().getUserId());

        if (plant == null) {
            return "error/errorIDDoNotExist";
        } else if (userService.getCurrentUser() != plant.getSeller() && plant.getSeller() != null){
            return "plantDetailOtherProfile";
        } else {
            return "error/errorPlantDetails";
        }
    }

    @PostMapping(path = "/plantDetailSeller/{id}")
    public String addToWishlist(@PathVariable("id") Integer plantId,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        Plant plant = plantService.findById(plantId);

        if (currentUser != null && plant != null) {
            currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plantId));

            userService.updateWishlist(currentUser);
        }

        model.put("currentUser", currentUser);
        return "redirect:/plantDetailSeller/{id}";
    }

    @PostMapping(path = "/plantDetailSeller/delete/{id}")
    public String removePlantFromWishlist(@PathVariable("id") Integer plantId,
                                          Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        currentUser.getWishlistPlants().remove(plantService.getPlantByPlantId(plantId));

        userService.updateWishlist(currentUser);


        model.put("currentUser", currentUser);
        return "redirect:/plantDetailSeller/{id}";
    }
}
