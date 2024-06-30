package com.example.sopra_pflanzenverkauf.controller;


import ch.qos.logback.core.model.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e, Model model) {
        System.out.println("Error: " + e);
        model.addText("error Ein Fehler ist aufgetreten: " + e.getMessage());
        return "error"; // Verweise auf eine spezifische Fehlerseite
    }
}
