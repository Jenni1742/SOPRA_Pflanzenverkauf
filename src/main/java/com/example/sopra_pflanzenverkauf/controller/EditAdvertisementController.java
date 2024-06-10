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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class EditAdvertisementController {
    @Autowired
    private PlantRepository plantRepository;

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Handles GET requests targeted at the editAdvertisement page.
     *
     * @return  the editAdvertisement page
     */
    @GetMapping("/editAdvertisement/{id}")
    public String editPlant(@PathVariable("id") Integer plantId, Model model) {
        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> {
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Plant not found");
                });
        model.addAttribute("plant", plant);

        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        return "editAdvertisement";
    }

    @RequestMapping(value="/editAdvertisement", method = RequestMethod.POST)
    public String editPlant(@ModelAttribute("plant") Plant updatePlant){

        User currentUser = userService.getCurrentUser();

        return "redirect:/myAdvertisements";
    }
}
