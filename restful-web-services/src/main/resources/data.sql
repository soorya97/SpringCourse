-- Allways use single quotes
insert into user values(10001, CURRENT_DATE(), 'AB');
insert into user values(10002, CURRENT_DATE(), 'Jill');
insert into user values(10003, CURRENT_DATE(), 'Jam');

insert into post values(11001, 'My first post', 10001);
insert into post values(11002, 'My second post', 10001);