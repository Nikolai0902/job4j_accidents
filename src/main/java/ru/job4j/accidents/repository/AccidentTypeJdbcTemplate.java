package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeJdbcTemplate {

    private final JdbcTemplate jdbc;

    public Optional<AccidentType> save(AccidentType accidentType) {
        jdbc.update("insert into accidents_type (name) values (?)",
                accidentType.getName()
        );
        return Optional.of(accidentType);
    }

    public List<AccidentType> findAll() {
        return jdbc.query("select id, name from accidents_type",
                new BeanPropertyRowMapper<>(AccidentType.class));
    }

    public Optional<AccidentType> findById(int id) {
        AccidentType rsl = jdbc.queryForObject(
                "select id, name from accidents_type where id = ?",
                new BeanPropertyRowMapper<>(AccidentType.class),
                id);
        return Optional.ofNullable(rsl);
    }

    public boolean update(AccidentType accidentType) {
        return jdbc.update(
                "update accidents_type set name = ? where id = ?",
                accidentType.getName(),
                accidentType.getId()) > 0;
    }

    public boolean delete(int id) {
        return jdbc.update("delete accidents_type where id = ?", id) != 0;
    }
}
