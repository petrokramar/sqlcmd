DROP TABLE IF EXISTS users;

CREATE  TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR (45) NOT NULL,
  password VARCHAR (45) NOT NULL,
  email VARCHAR (45) NOT NULL
);

INSERT INTO users (login, password, email) VALUES('login1', 'password1', 'address1@zzz.com');
INSERT INTO users (login, password, email) VALUES('login2', 'password2', 'address2@zzz.com');
INSERT INTO users (login, password, email) VALUES('login3', 'password3', 'address3@zzz.com');
INSERT INTO users (login, password, email) VALUES('login4', 'password4', 'address4@zzz.com');
INSERT INTO users (login, password, email) VALUES('login5', 'password5', 'address5@zzz.com');

DROP TABLE IF EXISTS connections;

CREATE  TABLE connections (
  id SERIAL PRIMARY KEY,
  db_name VARCHAR (45),
  db_user_name VARCHAR (45)
);

DROP TABLE IF EXISTS user_actions;

CREATE  TABLE user_actions (
  id SERIAL PRIMARY KEY,
  date TIMESTAMP,
  user_id INTEGER REFERENCES users(id) ,
  connection_id INTEGER REFERENCES connections(id) ,
  action VARCHAR (200)
);

DROP TABLE IF EXISTS users_test;

CREATE  TABLE users_test (
  id SERIAL PRIMARY KEY,
  name varchar(45) NOT NULL,
  password varchar(45) NOT NULL
);
