package com.twic.servlets;

import com.twic.model.VilleModel;
import com.twic.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VillesServlet {

    private static final int MAX_VILLE_PER_PAGE = 50;

    @Autowired
    ApiService service;

    @GetMapping("/villes")
    public String getListeVille(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {

        VilleModel[] villesPage = new VilleModel[MAX_VILLE_PER_PAGE];
        List<VilleModel> villes= service.getVilles();

        int nbPages = villes.size() / MAX_VILLE_PER_PAGE;

        for(int i=0; i<MAX_VILLE_PER_PAGE; i++) {
            villesPage[i] = villes.get((50*(page-1)) + i); //page -1 car le numéro de page commence à 1 au lieu de 0
        }

        model.addAttribute("nbPages", nbPages);
        model.addAttribute("villes", villesPage);
        return "villes";
    }
}
