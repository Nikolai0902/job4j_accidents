package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

@Repository
public class AccidentMem {

    Map<Integer, Accident> accidents = new Hashtable<>();

    {
        accidents.put(1, new Accident(1, "Nik", "BMW", "st. Green"));
        accidents.put(2, new Accident(2, "Bob", "Audi", "st. Red"));
        accidents.put(3, new Accident(3, "Tom", "BMW", "st. Black"));
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }
}
