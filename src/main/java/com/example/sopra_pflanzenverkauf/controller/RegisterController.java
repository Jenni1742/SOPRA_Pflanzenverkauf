package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.Plant;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.LevelService;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;
    @Autowired
    private LevelService levelService;

    @Autowired
    private MyUserValidator userValidator;
    @InitBinder("newUser")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(userValidator);
    }


    /**
     * Handles GET requests targeted at the register page.
     *
     * @return  the register page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(Model model) {

        return "register";
    }

    @PostMapping(path = "/register")
    public String changeProfile(@ModelAttribute("newUser") User newUser,
                                @RequestParam("newUsername") String newUsername,
                                @RequestParam("newFirstName") String newFirstName,
                                @RequestParam("newLastName") String newLastName,
                                @RequestParam("newEmail") String newEmail,
                                @RequestParam("newPLZ") String newPLZ,
                                @RequestParam("newPicturePath") String newPicturePath,
                                @RequestParam("password1") String password1,
                                @RequestParam("password2") String password2,
                                Model model) {

        if (userService.getUserByUsername(newUsername) == null) {
            if (userService.getUserByEmail(newEmail) != null) {
                model.addAttribute("mailExistiertBereits","Diese Email Adresse existiert bereits.");
                return "register";
            } else {
                if (password1.equals(password2)) {

                    newUser.setUsername(newUsername);
                    userService.updateUsername(newUser);

                    newUser.setFirstName(newFirstName);
                    userService.updateFirstName(newUser);

                    newUser.setLastName(newLastName);
                    userService.updateLastName(newUser);

                    newUser.setEmail(newEmail);
                    userService.updateEmail(newUser);

                    newUser.setPlz(newPLZ);
                    userService.updatePLZ(newUser);

                    newUser.setPicturePath(newPicturePath);
                    userService.updatePicturePath(newUser);

                    newUser.setPassword(password1);
                    userService.updateUserPassword(newUser);

                    newUser.setBuyingLevel(levelService.getLevelByLevelname("Korn"));
                    userService.updateBuyingLevel(newUser);
                    newUser.setSellingLevel(levelService.getLevelByLevelname("Korn"));
                    userService.updateSellingLevel(newUser);


                    userService.persistUser(newUser);

                    return "login";
                } else {
                    model.addAttribute("passwortGleich", "Die Passwörter stimmen nicht überein!");
                    return "register";
                }
            }
        } else {
            model.addAttribute("usernameExistiertBereits","Dieser Benutzername existiert bereits.");
            return "register";
        }
    }

    /*
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
     */
}
