-- Database sqlcmd_log
DROP TABLE IF EXISTS user_actions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS connections;

CREATE  TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR (45) NOT NULL,
  password VARCHAR (45) NOT NULL,
  email VARCHAR (45) NOT NULL
);

INSERT INTO users (id, login, password, email) VALUES('1', 'admin', 'admin', 'admin@zzz.com');
INSERT INTO users (id, login, password, email) VALUES('2', 'user', 'user', 'user@zzz.com');

CREATE  TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR (45) NOT NULL
);

INSERT INTO roles (id, name) VALUES('1', 'administrator');
INSERT INTO roles (id, name) VALUES('2', 'user');

CREATE  TABLE user_roles (
  user_id integer NOT NULL REFERENCES users(id),
  role_id integer NOT NULL REFERENCES roles(id),
  PRIMARY KEY(user_id, role_id)
);

INSERT INTO user_roles (user_id, role_id) VALUES('1','1');
INSERT INTO user_roles (user_id, role_id) VALUES('1','2');
INSERT INTO user_roles (user_id, role_id) VALUES('2','2');

CREATE  TABLE connections (
  id SERIAL PRIMARY KEY,
  db_name VARCHAR (45),
  db_user_name VARCHAR (45)
);

CREATE  TABLE user_actions (
  id SERIAL PRIMARY KEY,
  date TIMESTAMP NOT NULL,
  user_id INTEGER NOT NULL REFERENCES users(id) ,
  connection_id INTEGER REFERENCES connections(id) ,
  action VARCHAR (200)
);

-- DROP TABLE IF EXISTS users_test;
--
-- CREATE  TABLE users_test (
--   id SERIAL PRIMARY KEY,
--   name varchar(45) NOT NULL,
--   password varchar(45) NOT NULL
-- );
