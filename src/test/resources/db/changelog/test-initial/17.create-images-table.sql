create table images(
    id uuid primary key,
    object_name varchar not null,
    bucket_name varchar not null
);