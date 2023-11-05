package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import java.util.HashSet;
import java.util.Set;

@Repository
@AllArgsConstructor
public class RuleJdbcTemplate {

    private final JdbcTemplate jdbc;

    public Set<Rule> findBySet(Set<Integer> setId) {
        Set<Rule> rules = new HashSet<>();
        if (setId != null) {
            for (Integer i : setId) {
                rules.add(jdbc.queryForObject("select id, name from rule where id = ?",
                        new BeanPropertyRowMapper<>(Rule.class), i));
            }
        }
        return rules;
    }

    public Set<Rule> findByIdAccident(Set<Integer> setId) {
        Set<Rule> rules = new HashSet<>();
        if (setId != null) {
            for (Integer i : setId) {
                rules.add(jdbc.queryForObject("select id, name from rule where id = ?",
                        new BeanPropertyRowMapper<>(Rule.class), i));
            }
        }
        return rules;
    }

    public Rule save(Rule rule) {
        jdbc.update("insert into rule (name) values (?)",
                rule.getName());
        return null;
    }

    public Set<Rule> findAll() {
        return new HashSet<>(jdbc.query("select id, name from rule",
                new BeanPropertyRowMapper<>(Rule.class)));
    }
}
