package ru.waveaccess.test.conference.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController {

    @PreAuthorize("permitAll()")
    @GetMapping("/signIn")
    public String getSignIn(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "sign_in";
    }
}

