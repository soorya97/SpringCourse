-- Even though we used camel cases in the java code, SQL will transform them into _ for all column and table names

insert into currency_exchange (id, currency_from, currency_to, conversion_multiple, environment)
values (10001, 'USD', 'INR', 65, '');

insert into currency_exchange (id, currency_from, currency_to, conversion_multiple, environment)
values (10002, 'EUR', 'INR', 75, '');

insert into currency_exchange (id, currency_from, currency_to, conversion_multiple, environment)
values (10003, 'AUD', 'INR', 25, '');