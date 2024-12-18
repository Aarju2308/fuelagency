package com.faos.fuelagency.controllers;

import com.faos.fuelagency.entities.Cylinder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FuelagencyController {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/error")
    public String handleError(Model model) {
        model.addAttribute("errorMessage", "We encountered an unexpected error. Please contact support if the issue persists.");
        return "error-page";
    }

}
