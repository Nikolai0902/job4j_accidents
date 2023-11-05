package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
@Slf4j
public class RuleHibernate {

    private final CrudRepository crudRepository;

    public Optional<Rule> save(Rule rule) {
        try {
            crudRepository.run(session -> session.save(rule));
        } catch (Exception e) {
            log.error("create rule", e);
        }
        return Optional.of(rule);
    }

    public Set<Rule> findAll() {
        return new HashSet<>(crudRepository.query("from Rule", Rule.class));
    }

    public Set<Rule> findBySet(Set<Integer> setId) {
        return new HashSet<>(crudRepository.query(
                "from Rule r where r.id IN :rulesIds", Rule.class,
                Map.of("rulesIds", setId)
        ));
    }
}
