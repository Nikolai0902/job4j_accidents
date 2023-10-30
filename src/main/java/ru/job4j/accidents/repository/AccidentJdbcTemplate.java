package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate {

    private final JdbcTemplate jdbc;
    private final AccidentTypeJdbcTemplate accidentTypeJdbcTemplate;

    public Optional<Accident> create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into accidents (name, text, address, accidents_type_id) values (?, ?, ?, ?)",
                    new String[]{"id"});
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);

        var keys = keyHolder.getKeyList();
        int generatedId = (int) keys.get(0).get("id");
        accident.setId(generatedId);

        accident.getRules().forEach(r ->
                jdbc.update("insert into accidents_rule (accident_id, rule_id) values (?, ?)",
                accident.getId(), r.getId()));
        return Optional.of(accident);
    }

    public List<Accident> findAll() {
        return jdbc.query("select id, name, text, address, accidents_type_id from accidents",
                getAccident());
    }

    public Optional<Accident> findById(int id) {
        Accident rsl = jdbc.queryForObject(
                "select id, name, text, address, accidents_type_id from accidents where id = ?",
                getAccident(), id);
        return Optional.ofNullable(rsl);
    }

    public boolean update(Accident accident, Set<Rule> rules) {
        jdbc.update("delete from accidents_rule where accident_id = ?", accident.getId());
        for (Rule rule : rules) {
            jdbc.update("insert into accidents_rule (accident_id, rule_id) values (?, ?)",
                    accident.getId(), rule.getId());
        }
        return jdbc.update(
                "update accidents set name = ?,  text = ?, address = ?  where id = ?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId()) > 0;
    }

    public boolean delete(int id) {
        jdbc.update("delete from accidents_rule where accident_id = ?", id);
        return jdbc.update("delete from accidents where id = ?", id) != 0;
    }

    private RowMapper<Accident> getAccident() {
        return (rs, rowNum) -> {
            Accident accident = new Accident();
            accident.setId(rs.getInt("id"));
            accident.setName(rs.getString("name"));
            accident.setText(rs.getString("text"));
            accident.setAddress(rs.getString("address"));
            accident.setType(accidentTypeJdbcTemplate.findById(rs.getInt("accidents_type_id")).get());
            accident.setRules(new HashSet<>(jdbc.query("""
                                select rule.id, rule.name
                                from accidents
                                join accidents_rule on accidents_rule.accident_id = accidents.id
                                join rule on rule.id = accidents_rule.rule_id
                                where accidents.id = ?
                                """,
                    new BeanPropertyRowMapper<>(Rule.class), accident.getId())));
            return accident;
        };
    }
}