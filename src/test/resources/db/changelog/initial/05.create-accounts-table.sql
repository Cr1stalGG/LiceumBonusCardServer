create table accounts(
    id uuid primary key,
    login varchar(40) not null unique,
    password varchar(64) not null,
    first_name varchar(30) not null,
    last_name varchar(30) not null,
    father_name varchar(30) not null,
    phone_number varchar(20) not null,
    grade int not null,
    card_id uuid unique references cards(id),
    institution_id uuid references institutions(id),
    image_id uuid references images(id)
);