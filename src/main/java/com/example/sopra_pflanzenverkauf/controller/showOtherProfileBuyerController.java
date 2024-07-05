package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import com.example.sopra_pflanzenverkauf.service.PlantService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Controller
public class showOtherProfileBuyerController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PlantService plantService;

    @GetMapping("/showOtherProfileBuyer/{userid}")
    public String showUserDetailBuyer(@PathVariable("userid") Integer userId, Model model) {
        model.addAttribute("currentUser", userService.getCurrentUser());
        User user = userRepository.findById(userId)
                .orElse(null);
        model.addAttribute("user", user);
        if (user == null) {
            return "error/errorIDDoNotExist";
        } else {
            return "showOtherProfileBuyer";
        }
    }


    @PostMapping(path = "/showOtherProfileBuyer")
    public String addToWishlist(@RequestParam("id") Integer plant,
                                Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plant).getPlantname());

        if (currentUser != null && plant != null) {
            currentUser.getWishlistPlants().add(plantService.getPlantByPlantId(plant));

            userService.updateWishlist(currentUser);
        }

        Plant plantObject = plantService.getPlantByPlantId(plant);
        Integer userId = plantObject.getSeller().getUserId();

        model.put("currentUser", currentUser);
        return "redirect:/showOtherProfileSeller/" + userId + "#Verkaufsanzeigen";
    }

    @PostMapping(path = "/showOtherProfileBuyer/delete")
    public String removePlantFromWishlist(@RequestParam("plant") Integer plant,
                                          Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();
        System.out.println(plantService.getPlantByPlantId(plant).getPlantname());

        currentUser.getWishlistPlants().remove(plantService.getPlantByPlantId(plant));

        userService.updateWishlist(currentUser);


        Plant plantObject = plantService.getPlantByPlantId(plant);
        Integer userId = plantObject.getSeller().getUserId();

        model.put("currentUser", currentUser);
        return "redirect:/showOtherProfileSeller/" + userId + "#Verkaufsanzeigen";
    }
}
