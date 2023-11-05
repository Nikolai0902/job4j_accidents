package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleHibernate;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class RuleServiceHibernate {

    private final RuleHibernate ruleHibernate;

    public Optional<Rule> create(Rule rule) {
        return ruleHibernate.save(rule);
    }

    public Set<Rule> findAll() {
        return ruleHibernate.findAll();
    }

    public Set<Rule> findBySet(Set<Integer> set) {
        return ruleHibernate.findBySet(set);
    }
}
