package com.softserve.itacademy.controller;

import com.softserve.itacademy.dto.userDto.CreateUserDto;
import com.softserve.itacademy.dto.userDto.UpdateUserDto;
import com.softserve.itacademy.dto.userDto.UserDto;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.model.UserRole;
import com.softserve.itacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "create-user";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result) {
        //TODO
        return "redirect:/todos/all/users/" + user.getId();
    }

    @GetMapping("/{id}/read")
    public String read(@PathVariable long id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable long id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        return "update-user";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable long id, Model model,
                         @Validated @ModelAttribute("user") UpdateUserDto updateUserDto, BindingResult result) {
        UserDto oldUser = userService.findByIdThrowing(id);

        if (result.hasErrors()) {
            updateUserDto.setRole(oldUser.getRole()); // fallback to the current role
            model.addAttribute("roles", UserRole.values());
            return "update-user";
        }
        userService.update(updateUserDto);
        return "redirect:/users/" + id + "/read";
    }

    @PostMapping("/{id}/change-password")
    public String changePassword(@PathVariable long id, @RequestParam("oldPassword") String oldPassword,
                         @RequestParam("role") UserRole userRole, Model model,
                         @Validated @ModelAttribute("user") User user, BindingResult result) {

        throw new RuntimeException("Not implemented");
        //TODO
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getId() == id) {
            userService.delete(id);
            return "redirect:/login";
        }
        userService.delete(id);
        return "redirect:/users/all";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}
