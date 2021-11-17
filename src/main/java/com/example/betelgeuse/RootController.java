package com.example.betelgeuse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    @RequestMapping("/")
    public String root() {
        return "/index";
    }
    
    @PostMapping("/index")
    public String index() {
    	return "index";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }

}
