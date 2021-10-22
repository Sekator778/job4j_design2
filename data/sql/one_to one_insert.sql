insert into passport1(seria, number) VALUES (
                                             10, 1
                                            ),
                                            (20, 1),
                                            (30, 1);
insert into people1(name, pasport1_id) VALUES (
                                               'Sasha', 1),
                                              ('Masha', 2),
                                              ('Dasha', 3);
truncate table people1 restart identity ;
