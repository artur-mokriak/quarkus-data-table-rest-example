CREATE TABLE country
(
    id   UUID DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(256)                    NOT NULL,
    code VARCHAR(2)                      NOT NULL,
    CONSTRAINT pk_country PRIMARY KEY (id)
);