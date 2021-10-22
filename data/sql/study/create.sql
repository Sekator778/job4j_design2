create table universities
(
    id   serial primary key,
    name text
);

create table students
(
    id            serial primary key,
    name          text,
    course        int,
    budget        bool,
    speciality    text,
    enroll_date   timestamp,
    university_id int references universities (id)
);