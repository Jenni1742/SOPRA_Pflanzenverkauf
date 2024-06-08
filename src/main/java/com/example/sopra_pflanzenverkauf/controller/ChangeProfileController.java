package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class ChangeProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Request Mapping after a successful login.
     *
     * @param model the model.
     * @return overview-page.
     */
    @RequestMapping(value = "/changeProfile", method = RequestMethod.GET)
    public String showMyUserprofilePage(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "changeProfile";


    }


    @PostMapping(path = "/changeProfile")
    public String changeProfile(@RequestParam("newPicturePath") String newPicturePath,
                                @RequestParam("newFirstName") String newFirstName,
                                @RequestParam("newLastName") String newLastName,
                                @RequestParam("newEmail") String newEmail,
                                @RequestParam("newPLZ") String newPLZ,
                                Model model) {

        User currentUser = userService.getCurrentUser();


        if (!newPicturePath.isEmpty()) {
            currentUser.setPicturePath(newPicturePath);
            userService.updatePicturePath(currentUser);
        }
        if (!newFirstName.isEmpty()) {
            currentUser.setFirstName(newFirstName);
            userService.updateFirstName(currentUser);
        }
        if (!newLastName.isEmpty()) {
            currentUser.setLastName(newLastName);
            userService.updateLastName(currentUser);
        }
        if (!newEmail.isEmpty()) {
            if (userService.getUserByEmail(newEmail) == null || userService.getUserByEmail(newEmail) == currentUser) {
                currentUser.setEmail(newEmail);
                userService.updateEmail(currentUser);
            } else {
                model.addAttribute("emailExistiertBereits","Diese Email Adresse existiert bereits");
                model.addAttribute("currentUser", currentUser);
                return "/changeProfile";
            }

        }
        if (!newPLZ.isEmpty()) {
            currentUser.setPlz(newPLZ);
            userService.updatePLZ(currentUser);
        }

        model.addAttribute("currentUser", currentUser);

        return "redirect:/myUserprofile";
    }
}
