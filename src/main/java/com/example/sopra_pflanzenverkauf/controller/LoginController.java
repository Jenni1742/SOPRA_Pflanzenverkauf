package com.example.sopra_pflanzenverkauf.controller;

        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    /**
     * Handles GET requests targeted at the login page.
     *
     * @return  the login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage() {
        return "login";
    }
}
