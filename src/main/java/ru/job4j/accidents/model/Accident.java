package ru.job4j.accidents.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Модель вида нарушителя.
 *
 * Set<Rule> rules:
 * При создании новый объект Accident, который содержит уже сохраненные в базе объекты Rule.
 * Поэтом SData будет пытаться сохранить не только Accident, но и вложенные Rule. Но они уже есть в базе.
 * Поэтому будет происходить откат транзакции. Чтобы это исправить,
 * нужно использовать не CascadeType.ALL, а CascadeType.MERGE.
 */
@NamedEntityGraph(
        name = "accident-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("type"),
                @NamedAttributeNode("rules"),
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "accidents")
public class Accident {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String text;
    private String address;

    @ManyToOne
    @JoinColumn(name = "accidents_type_id")
    private AccidentType type;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "accidents_rule",
            joinColumns = { @JoinColumn(name = "accident_id") },
            inverseJoinColumns = { @JoinColumn(name = "rule_id") }
    )
    private Set<Rule> rules = new HashSet<>();
}
