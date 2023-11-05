package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentTypeHibernate {

    private final CrudRepository crudRepository;

    public List<AccidentType> findAll() {
        return crudRepository.query("from AccidentType", AccidentType.class);
    }

    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional(
                "from AccidentType p WHERE p.id = :fId",
                AccidentType.class,
                Map.of("fId", id)
        );
    }
}
