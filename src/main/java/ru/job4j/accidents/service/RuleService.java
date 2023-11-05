package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleMem;

import java.util.Optional;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@Service
public class RuleService {

    private final RuleMem ruleMem;

    public Set<Rule> findAll() {
        return ruleMem.findAll();
    }

    public Set<Rule> findBySet(Set<Integer> set) {
        return ruleMem.findBySet(set);
    }
}
