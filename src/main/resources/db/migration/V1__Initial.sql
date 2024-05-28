create sequence spaceships_seq;
create table spaceships (
        id bigint not null,
        name varchar(100) not null,
        related_to varchar(255),
        primary key (id),
        CONSTRAINT UC_spaceships_Name UNIQUE (name)
)