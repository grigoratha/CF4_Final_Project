use cf4;

CREATE TABLE session (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255),
    role VARCHAR(255),
    token VARCHAR(255) NOT NULL,
    timestamp VARCHAR(255)
);