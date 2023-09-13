CREATE TABLE accommodation_type (
    id                     uuid
        CONSTRAINT accommodation_type_pk         PRIMARY KEY,
    name                  VARCHAR(255) UNIQUE    NOT NULL,
    start_date            date                   NOT NULL,
    end_date              date                   NOT NULL,
    adult_count           int2                   NOT NULL,
    children_count        int2                   NOT NULL
);


INSERT INTO accommodation_type(id, name, start_date, end_date, adult_count, children_count)
    VALUES ('af6ee78c-fd63-409e-8c93-e2f952494b90', 'Палатка', '1900-05-01', '1900-09-30', 2, 1),
           ('17ec0c91-fd63-409e-8c93-e2f952494b90', 'Дом', '1900-01-01', '1900-12-31', 4, 2);

CREATE TABLE accommodation (
    id                     uuid
        CONSTRAINT accommodation_pk PRIMARY KEY,
    name                   VARCHAR(255) UNIQUE    NOT NULL,
    is_active              boolean                NOT NULL DEFAULT true,
    accommodation_type_id  uuid                   NOT NULL
);


ALTER TABLE accommodation
    ADD CONSTRAINT accommodation_type_id_fk
        FOREIGN KEY (accommodation_type_id)
            REFERENCES accommodation_type;

INSERT INTO accommodation (id, name, accommodation_type_id)
VALUES
    ('ec4609a7-a69a-42f0-a9e0-186f80d320f1', 'Палатка 1', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('8b3dc85d-e6ca-4399-816e-3ffe78078fbf', 'Палатка 2', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('e014013c-f4b0-4249-95e8-1a81a7d6040b', 'Палатка 3', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('c2b46222-c5a6-4b8e-abd0-a54a09fe6be1', 'Палатка 4', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('d18849fd-6898-4064-b5fa-51f46b600866', 'Палатка 5', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('dcfb2037-71cb-487a-a092-5eba0d7bf68a', 'Палатка 6', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('453c80ef-718a-4b38-8fdf-7bf12cf9b4e0', 'Палатка 7', 'af6ee78c-fd63-409e-8c93-e2f952494b90'),
    ('d8eaa1d8-2e49-4317-b450-9795cb09db25', 'Домик 1', '17ec0c91-fd63-409e-8c93-e2f952494b90'),
    ('d60c79db-7310-47ac-a8c9-1692f60a6f2a', 'Домик 2', '17ec0c91-fd63-409e-8c93-e2f952494b90'),
    ('39c058e2-15ef-4a65-a522-4ba98e6eee75', 'Домик 3', '17ec0c91-fd63-409e-8c93-e2f952494b90'),
    ('d9811ef4-f015-4ef2-b938-729bde35100e', 'Домик 4', '17ec0c91-fd63-409e-8c93-e2f952494b90'),
    ('983e387a-9b60-4926-9c65-aaf97dd0d885', 'Домик 5', '17ec0c91-fd63-409e-8c93-e2f952494b90');


CREATE TABLE booking (
    id                     uuid
        CONSTRAINT booking_pk PRIMARY KEY,
    date_check_in         date                   NOT NULL,
    date_check_out        date                   NOT NULL,
    comment               VARCHAR(500),
    adult_count           int2                   NOT NULL,
    children_count        int2                   NOT NULL,
    city                  VARCHAR(255)           NOT NULL,
    is_active             boolean                NOT NULL,
    is_paid               boolean                NOT NULL,
    created_at            timestamp              NOT NULL,
    accommodation_id      uuid                   NOT NULL,
    profile_id            uuid                   NOT NULL
);


CREATE TABLE profile (
    id                     uuid
        CONSTRAINT profile_pk PRIMARY KEY,
    surname               VARCHAR(255)           NOT NULL,
    name                  VARCHAR(255)           NOT NULL,
    patronymic            VARCHAR(255),
    phone_number          VARCHAR(255)           NOT NULL,
    email                 VARCHAR(255) UNIQUE    NOT NULL,
    password              VARCHAR(255)           NOT NULL,
    role_id               uuid                   NOT NULL
);

CREATE TABLE role (
    id                     uuid
        CONSTRAINT role_pk PRIMARY KEY,
    name                  VARCHAR(255)           NOT NULL
);

ALTER TABLE profile
    ADD CONSTRAINT role_id_fk
        FOREIGN KEY (role_id)
            REFERENCES role;

ALTER TABLE booking
    ADD CONSTRAINT accommodation_id_fk
            FOREIGN KEY (accommodation_id)
                REFERENCES accommodation,
    ADD CONSTRAINT profile_id_fk
        FOREIGN KEY (profile_id)
            REFERENCES profile;

INSERT INTO role(id, name)
VALUES ('51490cc3-ff2f-493e-aff3-460cfc07d7a8', 'ROLE_ADMIN'),
       ('0a8711d2-231b-4d05-8f1c-1e86e0c57148', 'ROLE_USER');