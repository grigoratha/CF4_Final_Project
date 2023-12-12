use cf4;

CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    mileage BIGINT,
    color VARCHAR(255),
    engineCC BIGINT,
    engineHP BIGINT,
    description TEXT,
    imageURI VARCHAR(255)
);

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255),
    email VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE session (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255),
    role VARCHAR(255),
    token VARCHAR(255) NOT NULL,
    timestamp VARCHAR(255)
);

CREATE TABLE favorites (
    id BIGINT PRIMARY KEY,
    itemList TEXT
);

