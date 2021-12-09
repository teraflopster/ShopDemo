create table if not exists products
(
    id          integer default nextval('table_name_id_seq'::regclass) not null
        constraint table_name_pk
            primary key,
    name        varchar(255)                                           not null,
    cost        integer                                                not null,
    description varchar(1000),
    type        varchar(255)                                           not null,
    image       varchar(500)
);