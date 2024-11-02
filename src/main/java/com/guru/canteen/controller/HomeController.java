package com.guru.canteen.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHomePage() {
        return "index"; // This will load src/main/resources/templates/index.html
    }
}