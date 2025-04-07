CREATE TABLE agenda
(
    id          SERIAL PRIMARY KEY,
    title       varchar(255) NOT NULL,
    description varchar(255),
    startAt     timestamp,
    endAt       timestamp,
    createdAt   timestamp    not null,
    updatedAt   timestamp    not null
);

create table agenda_vote
(
    id        serial primary key,
    agenda_fk serial      not null,
    vote      varchar(5)  not null,
    voter     varchar(11) not null,
    createdAt timestamp   not null,

    CONSTRAINT AgendaVoteAgenda foreign key (agenda_fk) references agenda (id)
)