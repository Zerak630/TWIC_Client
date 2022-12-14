package com.twic.servlets;

import com.twic.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VillesServlet {

    @Autowired
    ApiService service;

    @GetMapping("/villes")
    public String getListeVille(Model model) {
        model.addAttribute("villes", service.getVilles());

        return "villes";
    }
}
