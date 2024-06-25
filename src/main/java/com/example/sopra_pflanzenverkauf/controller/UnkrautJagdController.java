package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/unkrautjagd")
public class UnkrautJagdController {

    @Autowired
    private UserService userService;
    public void updatePlantCoinForCurrentUser(String username, int newPlantCoinAmount) {
        userService.updatePlantCoin(username, newPlantCoinAmount);
    }
    @GetMapping
    public String getGamePage(Model model) {
        User user = userService.getCurrentUser();
        Integer plantCoinCount = user.getPlantCoinCount();
        model.addAttribute("plantCoinCount", plantCoinCount);
        return "unkrautjagd";
    }


    @PostMapping
    public String collectPlantCoins(@RequestParam("coinCount") Integer coinCount, Model model) {
        User user = userService.getCurrentUser();
        user.setPlantCoinCount(coinCount);
        userService.updateCoinCount(user);

        model.addAttribute("coinCount", coinCount);
        System.out.println("Updated Coin Count: " + coinCount);

        return "unkrautjagd";
    }

    @PostMapping("/unkrautjagd/update")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public ResponseEntity<?> updatePlantCoins(@RequestBody Map<String, Integer> payload) {
        Integer coinCount = payload.get("coinCount");
        User user = userService.getCurrentUser();
        user.setPlantCoinCount(coinCount);
        userService.updateCoinCount(user);
        System.out.println("Updated Coin Count (2): " + coinCount);
        return ResponseEntity.ok().build();
    }
}
