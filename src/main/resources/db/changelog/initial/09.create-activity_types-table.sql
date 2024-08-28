create table activity_types(
    id bigserial primary key,
    name varchar(60) not null,
    cost int default 0 check(cost >= 0),
    institution_id bigint references institutions(id)
);