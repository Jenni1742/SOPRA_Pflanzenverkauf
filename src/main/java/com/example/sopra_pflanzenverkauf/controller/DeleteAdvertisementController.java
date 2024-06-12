package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.PlantRepository;
import com.example.sopra_pflanzenverkauf.service.CategoryService;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class DeleteAdvertisementController {

    @Autowired
    private UserService userService;

    @Autowired
    private PlantService plantService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/deleteAdvertisement/{id}")
    public String openDeletePlant(@PathVariable("id") Integer plantId, Model model) {

        //model.addAttribute("plantId", plantId);

        return "deleteAdvertisement";
    }


    @PostMapping("/deleteAdvertisement/{id}")
    public String deletePlant(@PathVariable("id") Integer plantId, Model model) {

        User currentUser = userService.getCurrentUser();

        for (User user:userService.findAllUsers()) {
            if(user.getWishlistPlants().contains(plantService.getPlantByPlantId(plantId))){
                user.getWishlistPlants().remove(plantService.getPlantByPlantId(plantId));
            }
        }

        plantService.deletePlantByPlantId(plantId);

        return "myAdvertisements";
    }
}
