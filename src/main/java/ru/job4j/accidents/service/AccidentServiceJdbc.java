package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.AccidentJdbcTemplate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccidentServiceJdbc {

    private final AccidentJdbcTemplate accidentJdbcTemplate;
    private final AccidentTypeServiceJdbc accidentTypeServiceJdbc;
    private final RuleServiceJdbc ruleServiceJdbc;

    public Optional<Accident> create(Accident accident, Set<Integer> rIds) {
        var accidentType = accidentTypeServiceJdbc.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        accident.setRules(ruleServiceJdbc.findBySet(rIds));
        return accidentJdbcTemplate.create(accident);
    }

    public List<Accident> findAll() {
        return accidentJdbcTemplate.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentJdbcTemplate.findById(id);
    }

    public boolean update(Accident accident, Set<Integer> rIds) {
        Set<Rule> set = ruleServiceJdbc.findBySet(rIds);
        return accidentJdbcTemplate.update(accident, set);
    }

    public boolean delete(int id) {
        return accidentJdbcTemplate.delete(id);
    }
}
