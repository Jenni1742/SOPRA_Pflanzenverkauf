package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MyAdvertisementsController {

    @Autowired
    private PlantService plantService;

    @Autowired
    private UserService userService;

    @GetMapping("/myAdvertisements")
    public String showMyAdvertisementsPage(Model model) {
        User currentUser = userService.getCurrentUser();
        List<Plant> plantList = currentUser.getPlantsToSell();

        model.addAttribute("plantList", plantList);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("coinCount", currentUser.getPlantCoinCount());

        return "myAdvertisements";
    }


}