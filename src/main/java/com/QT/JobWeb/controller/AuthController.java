package com.QT.JobWeb.controller;

import com.QT.JobWeb.dto.RegistrationDto;
import com.QT.JobWeb.models.UserEntity;
import com.QT.JobWeb.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        return "login";
    }


    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        RegistrationDto user = new RegistrationDto();
        model.addAttribute("user", user);
        return "register";

    }

    @PostMapping("/register/save")
    public String saveRegister(@Valid @ModelAttribute("user") RegistrationDto user, BindingResult result, Model model) {
        UserEntity existingUser = userService.findByEmail(user.getEmail());
        if(existingUser != null && existingUser.getEmail()!= null && !existingUser.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUsername = userService.findByUsername(user.getUsername());
        if(existingUsername != null && existingUsername.getUsername()!= null && !existingUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }
}
