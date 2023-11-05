package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentTypeMem {

    private AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, AccidentType> accidentType = new ConcurrentHashMap<>();

    public AccidentTypeMem() {
        this.accidentType.put(id.getAndIncrement(), new AccidentType(id.get(), "Две машины"));
        this.accidentType.put(id.getAndIncrement(), new AccidentType(id.get(), "Машина и человек"));
        this.accidentType.put(id.getAndIncrement(), new AccidentType(id.get(), "Машина и велосипед"));
    }

    public List<AccidentType> findAll() {
        return new ArrayList<>(accidentType.values());
    }

    public Optional<AccidentType> findById(int id) {
        return Optional.of(accidentType.get(id));
    }
}
