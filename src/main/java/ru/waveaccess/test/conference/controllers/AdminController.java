package ru.waveaccess.test.conference.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.waveaccess.test.conference.dto.UserDto;
import ru.waveaccess.test.conference.services.AdminService;
import ru.waveaccess.test.conference.services.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final UserService userService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin")
    public String getAll(@RequestParam(value = "page", required = false) Integer page,
                         @RequestParam(value = "size", required = false) Integer size,
                         Model model
    ) {
        page = page == null ? 0 : page;
        size = size == null ? 15 : size;
        model.addAttribute("users", adminService.getAllUsers(page, size));
        return "admin_page";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        adminService.deleteUserById(id);
        return "redirect:/admin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/update")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userService.saveOrUpdate(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/role/{id}")
    public String changeUsersRole(@PathVariable("id") Long id) {
        adminService.changeUsersRole(id);
        return "redirect:/admin";
    }
}
