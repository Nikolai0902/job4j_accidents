CREATE TABLE accidents_rule (
     id serial primary key,
     accident_id int not null references accidents(id) not null,
     rule_id int not null references rule(id) not null,
     unique (accident_id, rule_id)
 );