create table institutions(
    id uuid primary key,
    name varchar unique not null,
    city varchar(60) not null,
    image_id uuid references images(id)
    --todo add some info
);