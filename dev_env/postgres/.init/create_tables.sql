create table accounts (
    id serial primary key,
    email varchar(64) unique not null,
    encodedPassword varchar(128) not null,
    isLocked boolean not null default false
);
create index accountEmail on accounts using hash(email);

create table profiles (
    account integer references accounts on delete cascade,
    username varchar(64) unique not null,
    avatar varchar(128)
);

create table permissions (
    id serial primary key,
    systemName varchar(32) unique not null,
    viewName varchar(64) unique not null
);

create table accountPermissions (
    account integer references accounts on delete cascade,
    permission integer references permissions on delete cascade,
    primary key (account, permission)
);

create table types (
    id serial primary key,
    viewName varchar(32) not null unique
);

create table titles (
    id serial primary key,
    name varchar(128) not null,
    cover varchar(128),
    type integer references types on delete set null on update cascade
);

create table accountTitles (
    account integer references accounts on delete cascade,
    title integer references titles on delete cascade,
    state varchar(16) not null,
    primary key (account, title)
);



