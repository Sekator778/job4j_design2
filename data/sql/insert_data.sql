insert into chip(name)
VALUES ('first name'),
       ('second name'),
       ('third name');
insert into powerunit(name)
VALUES ('superPower'),
       ('megaPower'),
       ('topPower');
insert into passportminer(number)
VALUES (1),
       (2),
       (3);
insert into miner(name, passport_id, powerunit_id, chip_id)
VALUES ('MinerLow', 1, 1, 1),
       ('MinerExtra', 2, 1, 1),
       ('MinerHigh', 3, 1, 3);
insert into ferma(name)
VALUES ('everest'),
       ('monblan');
insert into ferma_miner (ferma_id, miner_id)
values (1, 1),
       (1, 2),
       (2, 3);
