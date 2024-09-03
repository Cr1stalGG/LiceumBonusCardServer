create table groups(
    id uuid primary key,
    admin_id uuid references accounts(id),
    name varchar(60) unique not null,
    description text,
    institution_id uuid references institutions(id)
);