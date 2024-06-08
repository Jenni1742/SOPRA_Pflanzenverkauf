package com.example.sopra_pflanzenverkauf.controller;

import com.example.sopra_pflanzenverkauf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class BuyerRankingController {

    @Autowired
    private UserService userService;

    /**
     * Request Mapping for the Buyer Ranking Page.
     *
     * @param model the model.
     * @return Buyer Ranking-page.
     */
    @RequestMapping(value = "/buyerRanking", method = RequestMethod.GET)
    public String showBuyerRankingPage(Map<String, Object> model) {

        model.put("currentUser", userService.getCurrentUser());

        return "buyerRanking";
    }
}
