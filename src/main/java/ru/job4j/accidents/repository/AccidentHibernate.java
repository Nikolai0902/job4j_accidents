package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
@Slf4j
public class AccidentHibernate {

    private final CrudRepository crudRepository;

    public Optional<Accident> save(Accident accident) {
        try {
            crudRepository.run(session -> session.save(accident));
        } catch (Exception e) {
            log.error("create Accident", e);
        }
        return Optional.of(accident);
    }

    public List<Accident> findAll() {
        return crudRepository.query("select distinct a from Accident AS a"
                + " left JOIN FETCH a.type left JOIN FETCH a.rules order by a.id", Accident.class);
    }

    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                "SELECT DISTINCT a from Accident AS a left JOIN FETCH a.type left JOIN FETCH a.rules WHERE a.id = :fId",
                Accident.class,
                Map.of("fId", id)
        );
    }

    public boolean update(Accident accident) {
        boolean result = false;
        try {
            crudRepository.run(session -> session.update(accident));
            result = true;
        } catch (Exception e) {
            log.error("update Accident", e);
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        try {
            crudRepository.run("DELETE Accident WHERE id = :fId", Map.of("fId", id));
            result = true;
        } catch (Exception e) {
            log.error("delete Accident", e);
        }
        return result;
    }
}
