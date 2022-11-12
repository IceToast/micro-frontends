package de.nordakademie.microservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayImageController {

    @GetMapping(path = "/displayImage")
    public ModelAndView fragment(@RequestParam String url, @RequestParam(required = false) Integer rotate) {
        ModelAndView modelAndView = new ModelAndView("displayImage");

        if(rotate != null && rotate >= 0 && rotate < 360){
            modelAndView.addObject("rotateDeg", rotate + "deg");
        }
        
        modelAndView.addObject("title", "Display Image");
        modelAndView.addObject("imageUrl", url);
        return modelAndView;
    }

}
