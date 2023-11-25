CREATE TABLE patient (
  patient_id INTEGER IDENTITY(1000, 1),
  name VARCHAR(255) NOT NULL,
  age INTEGER NOT NULL,
  room VARCHAR(255) NOT NULL,
  doctor_name VARCHAR(255) NOT NULL,
  admit_date DATETIME DEFAULT CURRENT_TIMESTAMP ,
  expenses VARCHAR(255) NOT NULL,
  status VARCHAR(255) NOT NULL,
  mobile VARCHAR(10),
  email VARCHAR(255)
);