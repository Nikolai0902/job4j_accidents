package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

    private static int id;
    private static int count;
    private final Map<Integer, Accident> accidents;

    public AccidentMem(Map<Integer, Accident> accidents) {
        this.accidents = new ConcurrentHashMap<>();
        accidents.put(id++, new Accident(count++, "Nik", "BMW", "st. Green"));
        accidents.put(id++, new Accident(count++, "Bob", "Audi", "st. Red"));
        accidents.put(id++, new Accident(count++, "Tom", "BMW", "st. Black"));
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public Optional<Accident> create(Accident accident) {
        accident.setId(count++);
        return Optional.ofNullable(accidents.put(id++, accident));
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }
}