package com.twic.servlets;

import com.twic.model.VilleModel;
import com.twic.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DistanceServlet {

    @Autowired
    ApiService service;

    @GetMapping("/distance")
    public String getSelecteur(Model model) {
        model.addAttribute("villes",service.getVilles());

        return "distance";
    }

    @PostMapping("/distance")
    public String calculeDistance(HttpServletRequest request, Model model) {

        model.addAttribute("postMethod", true);
        model.addAttribute("villes",service.getVilles());

        double distance;

        if(request.getParameter("ville1").equals(request.getParameter("ville2"))) {
            distance = 0;
        } else {
            VilleModel ville1 = service.getVilleByCodeCommune(request.getParameter("ville1"));
            VilleModel ville2 = service.getVilleByCodeCommune(request.getParameter("ville2"));

            double ville1Lat = Double.parseDouble(ville1.getLatitude());
            double ville1Long = Double.parseDouble(ville1.getLongitude());
            double ville2Lat = Double.parseDouble(ville2.getLatitude());
            double ville2Long = Double.parseDouble(ville2.getLongitude());

            distance = 6371 * Math.acos(
                    Math.sin(ville1Lat) * Math.sin(ville2Lat) +
                            Math.cos(ville1Lat) * Math.cos(ville2Lat) * Math.cos(ville2Long-ville1Long)
            );
        }

        model.addAttribute("result", distance);
        return "distance";
    }
}
