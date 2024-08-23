create table accounts_roles(
    id bigserial primary key,
    account_id bigint references accounts(id),
    role_id bigint references roles(id)
);