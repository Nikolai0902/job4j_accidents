package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepositorySD;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AccidentServiceSD {

    private final AccidentRepositorySD accidentRepository;
    private final AccidentTypeServiceSD accidentTypeServiceSD;
    private final RuleServiceSD ruleServiceSD;

    public void create(Accident accident, Set<Integer> rIds) {
        var accidentType = accidentTypeServiceSD.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        accident.setRules(ruleServiceSD.findBySet(rIds));
        accidentRepository.save(accident);
    }

    public List<Accident> findAll() {
        List<Accident> accidentList = new ArrayList<>();
        accidentRepository.findAll().forEach(accidentList::add);
        return accidentList;
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident, Set<Integer> rIds) {
        var accidentType = accidentTypeServiceSD.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        accident.setRules(ruleServiceSD.findBySet(rIds));
        return accidentRepository.save(accident).getId() == accident.getId();
    }
}
