package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MyProgressController {

    @Autowired
    private UserService userService;

    /**
     * Request Mapping for myProgress page.
     *
     * @param model the model.
     * @return myProgress page.
     */
    @RequestMapping(value = "/myProgress", method = RequestMethod.GET)
    public String showMyUserprofilePage(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "myProgress";


    }
}
