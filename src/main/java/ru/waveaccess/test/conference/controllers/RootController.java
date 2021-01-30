package ru.waveaccess.test.conference.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {
    @GetMapping("/")
    public String redirectFromRoot(Authentication authentication){
        if (authentication != null) {
            return "redirect:/main";
        } else {
            return "redirect:/signIn";
        }
    }
}
