package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AuthorityServiceSD;
import ru.job4j.accidents.service.UserServiceSD;

@Controller
@AllArgsConstructor
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserServiceSD users;
    private final AuthorityServiceSD authorities;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        if (users.save(user).isPresent()) {
            return "redirect:/login";
        }
        model.addAttribute("errorMessage", "Пользователь уже существует");
        return "reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}