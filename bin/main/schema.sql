DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS department;

CREATE TABLE department
(
	id INTEGER PRIMARY KEY,
	name VARCHAR(128)
);

CREATE TABLE employee
(
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(128),
	joined_date DATE,
	department_id INTEGER,
	CONSTRAINT fk_department_id FOREIGN KEY (department_id) REFERENCES department(id)
);