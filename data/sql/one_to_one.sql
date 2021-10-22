create table passport1(
    id serial primary key ,
    seria int,
    number int
);
create table people1(
    id serial primary key ,
    name varchar(200),
    pasport1_id int references passport1(id) unique
);