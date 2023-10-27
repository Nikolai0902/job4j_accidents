package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.io.IOException;

@Controller
@Slf4j
@AllArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.create(accident);
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
        return "accident/editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident,
                         Model model) {
        var accidentOptional = accidentService.findById(accident.getId());
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент не найден");
            log.error(String.format("post id %d not found", accident.getId()));
            return "errors/error404";
        }
        if (!accidentService.update(accidentOptional.get())) {
            model.addAttribute("message", "При обновлении данных произошла ошибка");
            return "errors/error404";
        }
        return "redirect:/index";
    }
}
