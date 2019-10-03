-- begin SAMPLE_BOOK
create table SAMPLE_BOOK (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TITLE varchar(255),
    DESCRIPTION longvarchar,
    EBOOK_ID varchar(36),
    AUTHOR_ID varchar(36),
    --
    primary key (ID)
)^
-- end SAMPLE_BOOK
-- begin SAMPLE_AUTHOR
create table SAMPLE_AUTHOR (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end SAMPLE_AUTHOR
-- begin SAMPLE_CONTRACT
create table SAMPLE_CONTRACT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    --
    primary key (ID)
)^
-- end SAMPLE_CONTRACT
-- begin SAMPLE_CONTRACT_ATTACHMENT
create table SAMPLE_CONTRACT_ATTACHMENT (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FILE_ID varchar(36),
    COMMENT_ longvarchar,
    CONTRACT_ID varchar(36),
    --
    primary key (ID)
)^
-- end SAMPLE_CONTRACT_ATTACHMENT
