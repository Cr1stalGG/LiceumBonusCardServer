insert into cards(
    id,
    number,
    balance
) values (
    'fdd76f7f-af76-41af-ad60-00d5bbceb2e6',
    '0000000000000000',
     99999999
);

insert into institutions(
    id,
    name,
    city
) values (
    'f287d9ff-728e-4a8f-ad79-8985fe55b50b',
    'ROOT',
    'ROOT'
);

insert into accounts(
    id,
    first_name,
    last_name,
    father_name,
    phone_number,
    login,
    password,
    card_id,
    institution_id
) values (
    '3234088c-210a-4f13-9a7e-e6900a1e2036',
    'superadmin', --todo придумать чёт норм
    'root',
    'master',
    '+12312312312',
    'master_rootSuperadmin211337',
    '$2a$12$JJ6KGAbBax5m5VdUpBAT3OJ/I3IWoAu0W2EbAu.coLf.sSmGYUPYy', --YDVx6rcS4pJw
    'fdd76f7f-af76-41af-ad60-00d5bbceb2e6',
    'f287d9ff-728e-4a8f-ad79-8985fe55b50b'
    );

insert into accounts_roles(
    account_id,
    role_id
) values (
    '3234088c-210a-4f13-9a7e-e6900a1e2036',
    'ed22972e-5083-4da5-998c-068662cb1078'
);