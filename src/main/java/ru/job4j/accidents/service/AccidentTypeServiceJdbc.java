package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeJdbcTemplate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentTypeServiceJdbc {

    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;

    public Optional<AccidentType> save(AccidentType accidentType) {
        return accidentTypeJdbcTemplate.save(accidentType);
    }

    public List<AccidentType> findAll() {
        return accidentTypeJdbcTemplate.findAll();
    }

    public Optional<AccidentType> findById(int id) {
        return accidentTypeJdbcTemplate.findById(id);
    }

    public boolean update(AccidentType accidentType) {
        return accidentTypeJdbcTemplate.update(accidentType);
    }

    public boolean delete(int id) {
        return accidentTypeJdbcTemplate.delete(id);
    }
}
