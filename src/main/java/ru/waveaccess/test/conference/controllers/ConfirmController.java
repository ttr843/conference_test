package ru.waveaccess.test.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.waveaccess.test.conference.services.ConfirmService;

@Controller
@RequiredArgsConstructor
public class ConfirmController {
    private final ConfirmService confirmService;

    @GetMapping("/confirm/{confirm-code}")
    public String confirm(@PathVariable("confirm-code") String confirmCode,
                          Model model) {
        boolean isConfirmed = confirmService.confirm(confirmCode);
        model.addAttribute("isConfirmed", isConfirmed);
        return "confirm_page";
    }

}
