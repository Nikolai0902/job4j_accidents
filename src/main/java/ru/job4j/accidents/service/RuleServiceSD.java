package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleRepositorySD;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleServiceSD {

    private final RuleRepositorySD ruleRepositorySD;

    public Set<Rule> findAll() {
        Set<Rule> ruleSet = new HashSet<>();
        ruleRepositorySD.findAll().forEach(ruleSet::add);
        return ruleSet;
    }

    public Set<Rule> findBySet(Set<Integer> set) {
        Set<Rule> ruleSet = new HashSet<>();
        for (Integer i: set) {
            ruleSet.add(ruleRepositorySD.findById(i).get());
        }
        return ruleSet;
    }
}
