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