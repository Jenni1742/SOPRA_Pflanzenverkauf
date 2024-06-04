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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class CreateAdvertisementController {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    /**
     * Handles GET requests targeted at the createAdvertisement page.
     *
     * @return  the createAdvertisement page
     */
    @RequestMapping(value = "/createAdvertisement", method = RequestMethod.GET)
    public String showCreateAdvertisementsPage(Model model) {
        model.addAttribute("plant", new Plant());

        List<Category> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);

        return "createAdvertisement";
    }

    @RequestMapping (value="/createAdvertisement", method = RequestMethod.POST)
    public String createPlant(@ModelAttribute("plant") Plant newPlant){

        User currentUser = userService.getCurrentUser();
        newPlant.setSeller(currentUser);

        //Verkauft ist standardmäßig false
        plantService.persistPlant(newPlant);

        userService.getCurrentUser().getPlantsToSell().add(newPlant);

        return "redirect:/myAdvertisements";
    }

}
