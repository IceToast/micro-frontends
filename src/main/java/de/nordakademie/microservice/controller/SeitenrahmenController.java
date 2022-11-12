package de.nordakademie.microservice.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SeitenrahmenController {

    @GetMapping(path = "/index")
    public ModelAndView fragment(@RequestParam(required = false, defaultValue = "csi") String strategy) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("title", "Toaster");
        modelAndView.addObject("strategy", strategy);

        // Do not call the other microservices if strategy is csi
        if (strategy.equals("csi"))
            return modelAndView;

        String navigationFragment = getFragment(
                "https://nak.matthias-geissendoerfer.de/toaster24/navigation-js/fragment");
        String aktionFragment = getFragment(
                "https://nak.matthias-geissendoerfer.de/toaster24/aktion-mg/fragment");

        String artikelFragment = getFragment("https://nak.matthias-geissendoerfer.de/toaster24/artikel-tb/fragment");
        String footerFragment = getFragment(
                "https://nak.matthias-geissendoerfer.de/toaster24/footer-ts/fragment");

        modelAndView.addObject("navigationFragment", navigationFragment);
        modelAndView.addObject("aktionFragment", aktionFragment);
        modelAndView.addObject("artikelFragment", artikelFragment);
        modelAndView.addObject("footerFragment", footerFragment);

        return modelAndView;
    }

    private String getFragment(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response;

        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());

        } catch (Exception e) {
            System.out.println(e);
            return "Error";
        }

        return response.body();
    }
}