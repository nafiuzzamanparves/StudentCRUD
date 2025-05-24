CREATE TABLE EmployeeSB (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  designation VARCHAR(100),
  age INTEGER,
  address VARCHAR(500),
  dob DATE,
  salary NUMERIC(10,2)
);
