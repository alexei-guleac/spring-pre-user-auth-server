create EXTENSION IF NOT EXISTS "uuid-ossp";

create table user_roles
(
    id        bigserial not null,
    user_role varchar(255),
    primary key (id)
);

create TABLE user_role
(
    user_id   uuid    NOT NULL,
    role_id   bigint  NOT NULL
);

create table users_details
(
    id               uuid DEFAULT uuid_generate_v4(),
    email            varchar(255),
    encoded_password varchar(255),
    is_enabled       boolean,
    username         varchar(255),
    name             varchar(255),
    password         varchar(255),
    forename         varchar(255),
    surname          varchar(255),
    create_datetime  timestamp without time zone NOT NULL,
    create_user_name character varying(255)      NOT NULL,
    update_datetime  timestamp without time zone,
    update_user_name character varying(255),
    deleted          boolean                     NOT NULL,
    active           boolean                     NOT NULL,
    primary key (id)
);