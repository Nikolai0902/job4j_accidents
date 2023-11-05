package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentHibernate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentServiceHibernate {

    private final AccidentHibernate accidentsRepostiory;
    private final AccidentTypeServiceHibernate accidentTypeServiceHibernate;
    private final RuleServiceHibernate ruleServiceHibernate;

    public Optional<Accident> create(Accident accident, Set<Integer> rIds) {
        var accidentType = accidentTypeServiceHibernate.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        accident.setRules(ruleServiceHibernate.findBySet(rIds));
        return accidentsRepostiory.save(accident);
    }

    public List<Accident> findAll() {
        return accidentsRepostiory.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentsRepostiory.findById(id);
    }

    public boolean update(Accident accident, Set<Integer> rIds) {
        var accidentType = accidentTypeServiceHibernate.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        accident.setRules(ruleServiceHibernate.findBySet(rIds));
        return accidentsRepostiory.update(accident);
    }

    public boolean delete(int id) {
        return accidentsRepostiory.delete(id);
    }
}
