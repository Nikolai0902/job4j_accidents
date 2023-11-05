package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentServiceHibernate;
import ru.job4j.accidents.service.AccidentServiceJdbc;

@Controller
@AllArgsConstructor
public class IndexController {

    private final AccidentServiceHibernate accidentService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", "Buslaev Nikolai");
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
