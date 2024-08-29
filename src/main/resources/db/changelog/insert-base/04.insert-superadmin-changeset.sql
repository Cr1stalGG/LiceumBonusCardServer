insert into cards(
    number, balance
) values (
    '0000000000000000',
     99999999
);

insert into institutions(
    name,
    city
) values (
    'ROOT',
    'ROOT'
);

insert into accounts(
    first_name,
    last_name,
    father_name,
    phone_number,
    login,
    password,
    card_id,
    institution_id
) values (
    'superadmin', --todo придумать чёт норм
    'root',
    'master',
    '+12312312312',
    'master_rootSuperadmin211337',
    '$2a$12$JJ6KGAbBax5m5VdUpBAT3OJ/I3IWoAu0W2EbAu.coLf.sSmGYUPYy', --YDVx6rcS4pJw
    1,
    1
    );

insert into accounts_roles(
    account_id,
    role_id
) values (
    1,
    6
);