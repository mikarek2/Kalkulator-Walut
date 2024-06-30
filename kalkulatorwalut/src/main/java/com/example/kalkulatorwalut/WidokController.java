package com.example.kalkulatorwalut;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WidokController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
