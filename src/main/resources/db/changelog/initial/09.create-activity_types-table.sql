create table activity_types(
    id bigserial primary key,
    name varchar(60) unique not null,
    cost int default 0 check(cost >= 0)
);