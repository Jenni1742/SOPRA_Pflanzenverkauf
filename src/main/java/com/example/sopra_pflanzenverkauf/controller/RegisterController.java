package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Category;
import com.example.sopra_pflanzenverkauf.entity.Level;
import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.LevelService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private LevelService levelService;
    @Autowired
    private View error;

    /**
     * Handles GET requests targeted at the register page.
     *
     * @return  the register page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }


    @PostMapping(value="/register")
    public String createUserprofile(@ModelAttribute("newUser") User newUser,
                                    @RequestParam("email") String email,
                                    Model model){


        if (userService.getUserByEmail(email) == null || userService.getUserByEmail(email) == newUser) {
            newUser.setEmail(email);
            userService.updateEmail(newUser);
        } else {
            model.addAttribute("error","Error");
            //model.addAttribute("mailExistiertBereits","Diese Email Adresse existiert bereits.");
            return "redirect:/register";

        }


        newUser.setBuyingLevel(levelService.getLevelByLevelname("Sprössling"));
        userService.updateBuyingLevel(newUser);
        newUser.setSellingLevel(levelService.getLevelByLevelname("Sprössling"));
        userService.updateSellingLevel(newUser);


        userService.persistUser(newUser);


        return "redirect:/login";
    }

    @PostMapping(path = "/register/picture")
    public String changeProfile(@ModelAttribute("newUser") User newUser,
                                @RequestParam("picturePath") String picturePath,
                                Map<String, Object> model) {

        if (!picturePath.isEmpty()) {
            newUser.setPicturePath(picturePath);
            userService.updatePicturePath(newUser);
        }

        model.put("currentUser", newUser);

        return "redirect:register";
    }
}
