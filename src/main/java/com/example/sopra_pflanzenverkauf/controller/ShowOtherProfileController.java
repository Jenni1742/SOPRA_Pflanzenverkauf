package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ShowOtherProfileController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlantService plantService;

    @GetMapping("/showOtherProfile/{userid}")
    public String showUserDetail(@PathVariable("userid") Integer userId, Model model) {
        User user = userRepository.findById(userId)
                .orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("currentUser", userService.getCurrentUser());

        if (user == null) {
            return "error/errorIDDoNotExist";
        } else {
            return "showOtherProfile";
        }
    }

    @PostMapping(path = "/showOtherProfile")
    public String addToWishlist(@RequestParam("id") Integer plant,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plant).getPlantname());

        Plant plantObject = plantService.getPlantByPlantId(plant);

        if (currentUser != null && plant != null) {
            currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plant));

            userService.updateWishlist(currentUser);
        }

        Integer userId = plantObject.getSeller().getUserId();

        model.put("currentUser", currentUser);

        if (plantObject.getSeller().getPlantsToSell().get(0) == plantObject) {
            return "redirect:/showOtherProfile/" + userId + "#Verkaufsanzeigen";
        } else {
            return "redirect:/showOtherProfile/" + userId + "#" + plant;
        }


    }

    @PostMapping(path = "/showOtherProfile/delete")
    public String removePlantFromWishlist(@RequestParam("plant") Integer plant,
                                          Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plant).getPlantname());

        currentUser.getWishlistPlants().remove(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);

        Plant plantObject = plantService.getPlantByPlantId(plant);
        Integer userId = plantObject.getSeller().getUserId();

        model.put("currentUser", currentUser);
        if (plantObject.getSeller().getPlantsToSell().get(0) == plantObject) {
            return "redirect:/showOtherProfile/" + userId + "#Verkaufsanzeigen";
        } else {
            return "redirect:/showOtherProfile/" + userId + "#" + plant;
        }
    }
}


