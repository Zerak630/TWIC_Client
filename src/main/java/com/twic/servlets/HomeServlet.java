package com.twic.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeServlet {

    @GetMapping("/home")
    public String pageAccueil() {
        return "home";
    }
}
