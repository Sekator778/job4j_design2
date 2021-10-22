create table students(
                         id serial primary key, name text
);

create table subjects(
                         id serial primary key, name text
);

create table students_subjects(
                                  id serial primary key,
                                  mark float,
                                  std_id int references students(id),
                                  sbj_id int references subjects(id)
);

insert into students(name) values ('Аня'), ('Ваня'), ('Боря');
insert into subjects(name) values ('Математика'), ('Русский'), ('Информатика');
insert into students_subjects(std_id, sbj_id, mark) values (1, 1, 5), (1, 2, 5), (1, 3, 5);
insert into students_subjects(std_id, sbj_id, mark) values (2, 1, 5), (2, 2, 4), (2, 3, 4);
insert into students_subjects(std_id, sbj_id, mark) values (3, 1, 3), (3, 2, 5), (3, 3, 3);