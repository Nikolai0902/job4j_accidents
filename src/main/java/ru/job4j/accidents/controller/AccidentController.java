package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.*;

import java.util.Set;

@Controller
@Slf4j
@AllArgsConstructor
public class AccidentController {

    private final AccidentServiceJdbc accidentService;
    private final AccidentTypeServiceJdbc accidentTypeService;
    private final RuleServiceJdbc ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident,
                       @RequestParam(required = false) Set<Integer> rIds) {
        accidentService.create(accident, rIds);
        return "redirect:/index";
    }

    @GetMapping("/updateAccident/{id}")
    public String formUpdate(Model model, @PathVariable("id") int id) {
        var accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент не найден");
            log.error(String.format("post id %d not found", id));
            return "errors/error404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("rules", ruleService.findAll());
        return "accident/editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident,  @RequestParam(required = false) Set<Integer> rIds,
                         Model model) {
        if (!accidentService.update(accident, rIds)) {
            model.addAttribute("message", "При обновлении данных произошла ошибка");
            return "errors/error404";
        }
        return "redirect:/index";
    }
}
