package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem {

    private static int id;
    private static int count;
    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private final AccidentTypeMem accidentTypeMem;
    private final RuleMem ruleMem;

    public AccidentMem(AccidentTypeMem accidentTypeMem, RuleMem ruleMem) {
        this.accidentTypeMem = accidentTypeMem;
        this.ruleMem = ruleMem;
        this.accidents.put(id++, new Accident(count++, "Nik", "BMW", "st. Green",
                accidentTypeMem.findById(0).get(), ruleMem.findAll()));
        this.accidents.put(id++, new Accident(count++, "Bob", "Audi", "st. Red",
                accidentTypeMem.findById(1).get(), ruleMem.findAll()));
        this.accidents.put(id++, new Accident(count++, "Tom", "BMW", "st. Black",
                accidentTypeMem.findById(2).get(), ruleMem.findAll()));
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
