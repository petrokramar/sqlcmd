DROP TABLE IF EXISTS users;

CREATE  TABLE users (
  id SERIAL PRIMARY KEY,
  name varchar(45) NOT NULL,
  password varchar(45) NOT NULL
);

INSERT INTO users (name, password) VALUES('Name 1', 'password1');
INSERT INTO users (name, password) VALUES('Name 2', 'password2');
INSERT INTO users (name, password) VALUES('Name 3', 'password3');
INSERT INTO users (name, password) VALUES('Name 4', 'password4');
INSERT INTO users (name, password) VALUES('Name 5', 'password5');

DROP TABLE IF EXISTS users_test;

CREATE  TABLE users_test (
  id SERIAL PRIMARY KEY,
  name varchar(45) NOT NULL,
  password varchar(45) NOT NULL
);
