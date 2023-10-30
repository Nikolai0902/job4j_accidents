CREATE TABLE accidents_rule (
     id serial primary key,
     accident_id int not null references accidents(id),
     rule_id int not null references rule(id),
     unique (accident_id, rule_id)
 );