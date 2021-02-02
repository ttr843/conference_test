package ru.waveaccess.test.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.waveaccess.test.conference.dto.PresentationDto;
import ru.waveaccess.test.conference.dto.UserDto;
import ru.waveaccess.test.conference.mappers.PresentationMapper;
import ru.waveaccess.test.conference.mappers.UserMapper;
import ru.waveaccess.test.conference.models.User;
import ru.waveaccess.test.conference.security.UserDetailsImpl;
import ru.waveaccess.test.conference.services.PresentationService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PresentationController {
    private final PresentationService presentationService;
    private final UserMapper userMapper;

    @PreAuthorize("hasAuthority('PRESENTER')")
    @GetMapping("/presentation")
    public String getPresentationPage(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("presentations",
                presentationService.findAllByPresenter(user));
        return "presentation_page";
    }

    @PreAuthorize("hasAuthority('PRESENTER')")
    @PostMapping("/presentation")
    public String createPresentation(@Valid PresentationDto presentationDto,
                                     Authentication authentication) {
        if (presentationDto.getPresenters() == null || presentationDto.getPresenters().isEmpty()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            UserDto userDto = userMapper.toDto(user);
            List<UserDto> presenters = new ArrayList<>();
            presenters.add(userDto);
            presentationDto.setPresenters(presenters);
        }
        presentationService.saveOrUpdate(presentationDto);
        return "redirect:/presentation";
    }

    @PreAuthorize("hasAuthority('PRESENTER')")
    @PostMapping("/presentation/update")
    public PresentationDto update(@RequestBody PresentationDto presentationDto) {
        return presentationService.saveOrUpdate(presentationDto);
    }

    @PreAuthorize("hasAuthority('PRESENTER')")
    @PostMapping("/presentation/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        presentationService.deleteById(id);
        return "redirect:/presentation";
    }


}
