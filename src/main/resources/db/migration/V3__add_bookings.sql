create table bookings
(
    booking_id        int8 not null,
    booking_date      date not null,
    booking_ends_at   time not null,
    booking_starts_at time not null,
    room_id           int8 not null,
    user_id           int8 not null,
    primary key (booking_id),
    constraint fk_room_id_bookings
        foreign key (room_id) references rooms
            on update CASCADE
            on delete CASCADE,
    constraint fk_user_id_bookings
        foreign key (user_id) references users
            on update CASCADE
            on delete CASCADE
);