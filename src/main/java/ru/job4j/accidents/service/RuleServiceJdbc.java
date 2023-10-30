package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleJdbcTemplate;

import java.util.Set;

@Service
@AllArgsConstructor
public class RuleServiceJdbc {

    private final RuleJdbcTemplate ruleJdbcTemplate;

    public Set<Rule> findAll() {
        return ruleJdbcTemplate.findAll();
    }

    public Set<Rule> findBySet(Set<Integer> set) {
        return ruleJdbcTemplate.findBySet(set);
    }
}
