CREATE SCHEMA IF NOT EXISTS geo;

CREATE TABLE geo.province (

    id      BIGSERIAL   NOT NULL,
    name    TEXT        NOT NULL,


    -- technical columns
    t_active    BOOLEAN     NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX province_uq_name
    ON geo.province (name)
    WHERE t_active IS TRUE;

CREATE TABLE geo.town (
    id              BIGSERIAL   NOT NULL,
    province_id     BIGINT      NOT NULL,
    name            TEXT        NOT NULL,

    -- technical columns
    t_active    BOOLEAN     NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id),
    CONSTRAINT town_fk_province_id
        FOREIGN KEY (province_id)
            REFERENCES geo.province(id)
);

CREATE UNIQUE INDEX town_uq_name
    ON geo.town (name)
    WHERE t_active IS TRUE;

CREATE TABLE geo.terminal (
    id              BIGSERIAL   NOT NULL,
    town_id         BIGINT      NOT NULL,
    name            TEXT        NOT NULL,

    -- technical columns
    t_active    BOOLEAN     NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id),
    CONSTRAINT terminal_fk_town_id
        FOREIGN KEY (town_id)
            REFERENCES geo.town(id)

);

CREATE UNIQUE INDEX terminal_uq_name
    ON geo.terminal (name)
    WHERE t_active IS TRUE;
