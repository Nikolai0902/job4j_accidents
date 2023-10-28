package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private static AtomicInteger id;
    private static AtomicInteger count;
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AccidentTypeMem accidentTypeMem;
    private final RuleMem ruleMem;

    public AccidentMem(AccidentTypeMem accidentTypeMem, RuleMem ruleMem) {
        this.accidentTypeMem = accidentTypeMem;
        this.ruleMem = ruleMem;
        this.accidents.put(id.getAndIncrement(), new Accident(count.getAndIncrement(), "Nik", "BMW", "st. Green",
                accidentTypeMem.findById(0).get(), ruleMem.findAll()));
        this.accidents.put(id.getAndIncrement(), new Accident(count.getAndIncrement(), "Bob", "Audi", "st. Red",
                accidentTypeMem.findById(1).get(), ruleMem.findAll()));
        this.accidents.put(id.getAndIncrement(), new Accident(count.getAndIncrement(), "Tom", "BMW", "st. Black",
                accidentTypeMem.findById(2).get(), ruleMem.findAll()));
    }

    public List<Accident> findAll() {
        return new ArrayList<>(accidents.values());
    }

    public Optional<Accident> create(Accident accident) {
        accident.setId(count.getAndIncrement());
        return Optional.ofNullable(accidents.put(id.getAndIncrement(), accident));
    }

    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }

    public boolean update(Accident accident) {
        return accidents.replace(accident.getId(), accidents.get(accident.getId()), accident);
    }
}
