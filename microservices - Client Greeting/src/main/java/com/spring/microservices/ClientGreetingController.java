package com.spring.microservices;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ClientGreetingController {

    protected ClientGreetingService helloWorldService;

    //constructor
    public ClientGreetingController(ClientGreetingService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @RequestMapping("/greeting")
    public String goHome() {
        return "index";
    }

    @RequestMapping("/greeting/{name}")
    public String greeting(Model model, @PathVariable("name") String name) {

        Greeting greeting = helloWorldService.greeting(name);

        model.addAttribute("greeting", greeting.getContent());

        return "greeting";
    }
}

