package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class RuleMem {

    private AtomicInteger id = new AtomicInteger(0);
    private final Map<Integer, Rule> rules = new ConcurrentHashMap<>();

    public RuleMem() {
        this.rules.put(id.getAndIncrement(), new Rule(id.get(), "Статья 1"));
        this.rules.put(id.getAndIncrement(), new Rule(id.get(), "Статья 2"));
        this.rules.put(id.getAndIncrement(), new Rule(id.get(), "Статья 3"));
    }

    public Set<Rule> findAll() {
        return new HashSet<>(rules.values());
    }

    public Set<Rule> findBySet(Set<Integer> setId) {
        return rules.values().stream().filter(s -> setId.contains(s.getId())).collect(Collectors.toSet());
    }
}
