package ru.waveaccess.test.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.waveaccess.test.conference.dto.ScheduleDto;
import ru.waveaccess.test.conference.services.ScheduleService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PreAuthorize("hasAuthority('PRESENTER')")
    @PostMapping("/addSchedule")
    public String addPresentationToSchedule(@Valid ScheduleDto scheduleDto) {

        scheduleService.addPresentationToSchedule(scheduleDto);
        return "redirect:/main";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
    public String getMainPage(Model model) {
        model.addAttribute("map", scheduleService.findAllScheduleByRoom());
        return "main_page";
    }
}
