package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class CareTipsController {
    @Autowired
    private UserService userService;

    /**
     * Request Mapping after a successful login.
     *
     * @param model the model.
     * @return overview-page.
     */
    @RequestMapping(value = "/careTips", method = RequestMethod.GET)
    public String showCareTipsPage(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "careTips";
    }
}
