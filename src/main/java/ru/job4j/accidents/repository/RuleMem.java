package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class RuleMem {

    private static int id;
    private final List<Rule> rules = new ArrayList<>();

    public RuleMem() {
        this.rules.add(new Rule(id++, "Статья 1"));
        this.rules.add(new Rule(id++, "Статья 2"));
        this.rules.add(new Rule(id++, "Статья 3"));
    }

    public Set<Rule> findAll() {
        return Set.copyOf(rules);
    }

    public Set<Rule> findBySet(Set<Integer> setId) {
        return rules.stream().filter(s -> setId.contains(s.getId())).collect(Collectors.toSet());
    }
}
