insert into devices(name, price)
VALUES ('phone', 120.90),
       ('tv', 400.99),
       ('server', 999.99),
       ('clock', 25),
       ('pc_book', 290.50);
insert into people(name)
values ('Sasha'),
       ('Masha'),
       ('Dasha'),
       ('Pasha'),
       ('Ema');
insert into devices_people(device_id, people_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (2, 2),
       (1, 2),
       (4, 3),
       (5, 3),
       (1, 4),
       (4, 4),
       (5, 4);