CREATE TABLE accidents (
  id serial primary key,
  name text,
  text text,
  address text,
  accidents_type_id int not null references accidents_type (id)
);