package com.spring.microservices;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientGreetingHomeController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }
}