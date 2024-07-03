package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



import java.security.Principal;
import java.util.Locale;
import java.util.Map;

@Controller
public class MyUserprofileController {

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
    @RequestMapping(value = "/myUserprofile", method = RequestMethod.GET)
    public String showMyUserprofilePage(Map<String, Object> model) {
        // The following code may be used to redirect an admin to a specific admin-homepage instead of the default overview.
        // Of course this will work for other roles too.
        //if (userService.getCurrentUserDetails().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
        // redirect to another GET-request. You need another controller which would handle the request "/admin/"
        // As this controller is not defined yet, it is commented out.
        // return "redirect:/admin/";
        //}

        model.put("currentUser", userService.getCurrentUser());
        model.put("plantCoins", userService.getCurrentUser().getPlantCoinCount());

        return "myUserprofile";
    }
    @PostMapping(path = "/myUserprofile")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model) {
        User currentUser = userService.getCurrentUser();

        if (bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.updateUserPassword(currentUser);
            model.addAttribute("passwortErfolgreichGeändert", "Dein Passwort wurde erfolgreich geändert.");
        } else {
            model.addAttribute("altesPasswortIstNichtKorrekt", "Das eingegebene aktuelle Passwort ist inkorrekt, bitte versuche es erneut.");
        }

        model.addAttribute("currentUser", currentUser);
        return "/myUserprofile";
    }

/*
    @PostMapping(path = "/myUserprofile/")
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
                return "myUserprofile";
            }

        }
        if (!newPLZ.isEmpty()) {
            currentUser.setPlz(newPLZ);
            userService.updatePLZ(currentUser);
        }

        model.addAttribute("currentUser", currentUser);

        return "redirect:/myUserprofile";
    }*/


}
