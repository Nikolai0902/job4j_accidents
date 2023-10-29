package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem(AccidentTypeMem accidentTypeMem, RuleMem ruleMem) {
        this.accidents.put(id.getAndIncrement(), new Accident(id.get(), "Nik", "BMW", "st. Green",
                accidentTypeMem.findById(0).get(), ruleMem.findAll()));
        this.accidents.put(id.getAndIncrement(), new Accident(id.get(), "Bob", "Audi", "st. Red",
                accidentTypeMem.findById(1).get(), ruleMem.findAll()));
        this.accidents.put(id.getAndIncrement(), new Accident(id.get(), "Tom", "BMW", "st. Black",
                accidentTypeMem.findById(2).get(), ruleMem.findAll()));
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public Optional<Accident> create(Accident accident) {
        id.getAndIncrement();
        accident.setId(id.get());
        return Optional.ofNullable(accidents.put(accident.getId(), accident));
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }
}
