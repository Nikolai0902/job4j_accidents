INSERT INTO accidents_type (name) VALUES ('Две машины');
INSERT INTO accidents_type (name) VALUES ('BМашина и человекob');
INSERT INTO accidents_type (name) VALUES ('Машина и велосипед');

INSERT INTO rule (name) VALUES ('Статья 1');
INSERT INTO rule (name) VALUES ('Статья 2');
INSERT INTO rule (name) VALUES ('Статья 3');

INSERT INTO accidents (name, text, address, accidents_type_id) VALUES ('Nik', 'BMW', 'st. Green', 1);
INSERT INTO accidents (name, text, address, accidents_type_id) VALUES ('Bob', 'Audi', 'st. Red', 2);
INSERT INTO accidents (name, text, address, accidents_type_id) VALUES ('Tom', 'BMW', 'st. Black', 3);

INSERT INTO accidents_rule (accident_id, rule_id) VALUES (1, 2);
INSERT INTO accidents_rule (accident_id, rule_id) VALUES (1, 3);
INSERT INTO accidents_rule (accident_id, rule_id) VALUES (2, 1);
INSERT INTO accidents_rule (accident_id, rule_id) VALUES (2, 2);
INSERT INTO accidents_rule (accident_id, rule_id) VALUES (3, 1);
