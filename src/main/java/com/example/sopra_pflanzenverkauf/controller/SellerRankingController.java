package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class SellerRankingController {

    @Autowired
    private UserService userService;

    /**
     * Request Mapping for the Seller Ranking Page.
     *
     * @param model the model.
     * @return sellerRanking-page.
     */
    @RequestMapping(value = "/sellerRanking", method = RequestMethod.GET)
    public String showSellerRankingPage(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "sellerRanking";
    }
}
