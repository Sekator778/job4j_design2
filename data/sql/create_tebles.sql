create table chip
(
    id   serial primary key,
    name varchar(100)
);
create table powerUnit
(
    id   serial primary key,
    name varchar(100)
);
create table passportMiner
(
    id     serial primary key,
    number int
);
create table miner
(
    id           serial primary key,
    name         varchar(100),
    passport_id  int references passportMiner (id) unique,
    powerUnit_id int references powerUnit (id),
    chip_id      int references chip (id)
);
create table ferma
(
    id       serial primary key,
    name     varchar(100)
);
create table ferma_miner
(
    id serial primary key ,
    ferma_id int references ferma(id),
    miner_id int references miner(id)
);