package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@AllArgsConstructor
public class AccidentController {

    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAll());
        return "accident/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        var accidentType = accidentTypeService.findById(accident.getType().getId());
        accident.setType(accidentType.get());
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
        model.addAttribute("templates/accident", accidentOptional.get());
        return "accident/editAccident";
    }

    @PostMapping("/editAccident")
    public String update(@ModelAttribute Accident accident,
                         Model model) {
        if (!accidentService.update(accident)) {
            model.addAttribute("message", "При обновлении данных произошла ошибка");
            return "errors/error404";
        }
        return "redirect:/index";
    }
}
