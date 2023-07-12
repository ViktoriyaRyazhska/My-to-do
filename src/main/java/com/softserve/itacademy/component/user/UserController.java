package com.softserve.itacademy.component.user;

import com.softserve.itacademy.component.user.dto.CreateUserDto;
import com.softserve.itacademy.component.user.dto.UpdateUserDto;
import com.softserve.itacademy.component.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasAuthority('ADMIN') or isAnonymous()")
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new CreateUserDto());
        return "create-user";
    }

    @PreAuthorize("hasAuthority('ADMIN') or isAnonymous()")
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "create-user";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        User newUser = userService.create(user);
        return "redirect:/todos/all/users/" + newUser.getId();
    }

    @PreAuthorize("authentication.details.id == #id")
    @GetMapping("/{id}/read")
    public String read(@PathVariable long id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @PreAuthorize("authentication.details.id == #id")
    @GetMapping("/{id}/update")
    public String update(@PathVariable long id, Model model) {
        User user = userService.readById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", UserRole.values());
        return "update-user";
    }

    @PreAuthorize("hasAuthority('ADMIN') or authentication.details.id == #id")
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

    @PreAuthorize("hasAuthority('ADMIN') or authentication.details.id == #id")
    @PostMapping("/{id}/change-password")
    public String changePassword(@PathVariable long id, @RequestParam("oldPassword") String oldPassword,
                         @RequestParam("role") UserRole userRole, Model model,
                         @Validated @ModelAttribute("user") User user, BindingResult result) {

        throw new RuntimeException("Not implemented");

//        User oldUser = userService.readById(id);
//        if (result.hasErrors()) {
//            user.setRole(oldUser.getRole());
//            model.addAttribute("roles", UserRole.values());
//            return "update-user";
//        }
//        if (!passwordEncoder.matches(oldPassword, oldUser.getPassword())) {
//            result.addError(new FieldError("user", "password", "Old password is not correct!"));
//            user.setRole(oldUser.getRole());
//            model.addAttribute("roles", UserRole.values());
//            return "update-user";
//        }
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        if (oldUser.getRole() == UserRole.USER) {
//            user.setRole(oldUser.getRole());
//        } else {
//            user.setRole(userRole);
//        }
//        userService.update(user);
//        return "redirect:/users/" + id + "/read";
    }

    @PreAuthorize("authentication.details.id == #id")
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        User currentUser = userService.getCurrentUser();
        if (currentUser.getId() == id) {
            userService.delete(id);
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }
        userService.delete(id);
        return "redirect:/users/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users-list";
    }
}
