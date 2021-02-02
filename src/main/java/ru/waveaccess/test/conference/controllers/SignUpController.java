package ru.waveaccess.test.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.waveaccess.test.conference.dto.SignUpDto;
import ru.waveaccess.test.conference.services.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final UserService userService;

    @PreAuthorize("permitAll()")
    @GetMapping("/signUp")
    public String getSignUpPage(Authentication authentication) {
        if (authentication != null) {
            return "redirect:/";
        }
        return "sign_up";
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/signUp")
    public String signUp(@Valid SignUpDto signUpDto, BindingResult bindingResult, Model model) {
        if (!bindingResult.getAllErrors().isEmpty()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
        } else {
            userService.signUp(signUpDto);
        }
        return "sign_up";
    }
}
