package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.entity.User;
import com.example.sopra_pflanzenverkauf.repository.UserRepository;
import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class showOtherProfileBuyerController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

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
}
