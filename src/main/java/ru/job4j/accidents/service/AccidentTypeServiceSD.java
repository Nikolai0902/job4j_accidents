package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepositorySD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeServiceSD {

    private final AccidentTypeRepositorySD accidentTypeRepositorySD;

    public List<AccidentType> findAll() {
        return accidentTypeRepositorySD.findAll();
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeRepositorySD.findById(id);
    }
}
