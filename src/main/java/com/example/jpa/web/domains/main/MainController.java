package com.example.jpa.web.domains.main;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/main"})
    public String mainPage() {

        return "main";
    }

}
