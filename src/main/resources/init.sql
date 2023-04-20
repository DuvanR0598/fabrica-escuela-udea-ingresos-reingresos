CREATE TABLE IF NOT EXISTS roles
(
    id   bigint auto_increment
    PRIMARY KEY,
    value VARCHAR(255) NOT NULL
    ) engine = MyISAM;

TRUNCATE TABLE roles;

INSERT INTO roles (value)
VALUES
    ('ROLE_SUPER_ADMIN'),
    ('ROLE_ADMIN'),
    ('ROLE_USER');
