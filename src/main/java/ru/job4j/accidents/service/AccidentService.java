package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentMem;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class AccidentService {

    private final AccidentMem accidentRepository;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    public Optional<Accident> create(Accident accident, Set<Integer> rIds) {
        var accidentType = accidentTypeService.findById(accident.getType().getId());
        accident.setType(accidentType.get());
        accident.setRules(ruleService.findBySet(rIds));
        return accidentRepository.create(accident);
    }

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean update(Accident accident) {
        return accidentRepository.update(accident);
    }
}
