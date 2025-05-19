CREATE TABLE employee_file_upload (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    designation VARCHAR(100),
    age SMALLINT,
    address VARCHAR(500),
    dob DATE,
    salary NUMERIC(10,2),
    image VARCHAR(255)
);