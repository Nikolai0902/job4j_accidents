package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccidentTypeMem {

    private static int id;
    private final List<AccidentType> accidentTypeList = new ArrayList<>();

    public AccidentTypeMem() {
        this.accidentTypeList.add(new AccidentType(id++, "Две машины"));
        this.accidentTypeList.add(new AccidentType(id++, "Машина и человек"));
        this.accidentTypeList.add(new AccidentType(id++, "Машина и велосипед"));
    }

    public List<AccidentType> findAll() {
        return accidentTypeList;
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.of(accidentTypeList.get(id));
    }

}
