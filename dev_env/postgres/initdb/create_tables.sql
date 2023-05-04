create table accounts (
    id serial primary key,
    email varchar(64) unique,
    encodedPassword varchar(128),
    isLocked boolean default false
);
create index accountEmail on accounts using hash(email);

create table profiles (
    accountId integer references accounts on delete cascade,
    username varchar(64) unique,
    avatar varchar(128)
);

create table permissions (
    id smallserial primary key,
    systemName varchar(32) unique,
    viewName varchar(64)
);

create table accountPermissions (
    accountId integer references accounts on delete cascade,
    permissionId smallint references permissions on delete cascade,
    primary key (accountId, permissionId)
);

create table types (
    id smallserial primary key,
    systemName varchar(32) unique,
    viewName varchar(32)
);

create table titles (
    id serial primary key,
    name varchar(128),
    cover varchar(128),
    typeId smallint references types on delete set null
);

create table accountTitles (
    accountId integer references accounts on delete cascade,
    titleId integer references titles on delete cascade,
    state varchar(16),
    primary key (accountId, titleId)
);



