package de.nordakademie.microservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SeitenrahmenController {

    @GetMapping(path = "/index")
    public ModelAndView fragment(@RequestParam(required = false) String strategy) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "Toaster");

        


        return modelAndView;
    }
}