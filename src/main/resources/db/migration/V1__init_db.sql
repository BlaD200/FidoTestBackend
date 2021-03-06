create table rooms
(
    room_id     int8        not null generated by default as identity,
    name        VARCHAR(50) not null,
    location    VARCHAR(256),
    seats_count int4        not null,
    primary key (room_id),
    constraint unique_room_name unique (name)
);

create table users
(
    user_id   int8         not null generated by default as identity,
    email     VARCHAR(100) not null,
    full_name VARCHAR(50)  not null,
    password  varchar(255) not null,
    primary key (user_id),
    constraint unique_user_email unique (email)
);