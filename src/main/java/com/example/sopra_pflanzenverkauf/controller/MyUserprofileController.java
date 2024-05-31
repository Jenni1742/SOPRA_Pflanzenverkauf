package com.example.sopra_pflanzenverkauf.controller;

import ch.qos.logback.core.model.Model;
import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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

        return "myUserprofile";
    }
    @PostMapping("/myUserprofile/changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Map<String, Object> model) {
        User currentUser = userService.getCurrentUser();

        if (bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
            currentUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.updateUserPassword(currentUser);
            model.put("message", "Passwort erfolgreich geändert.");
        } else {
            model.put("error", "Altes Passwort ist inkorrekt.");
        }

        model.put("currentUser", currentUser);
        return "myUserprofile";
    }


}
