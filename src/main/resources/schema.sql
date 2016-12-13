-- Database sqlcmd_log
DROP TABLE IF EXISTS user_actions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS connections;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL PRIMARY KEY,
  password VARCHAR(45) NOT NULL ,
  enabled INTEGER NOT NULL DEFAULT 1
);

INSERT INTO users(username,password)
VALUES ('admin','1234');
INSERT INTO users(username,password)
VALUES ('user','1234');

CREATE TABLE user_roles (
  user_role_id SERIAL PRIMARY KEY,
  username varchar(45) NOT NULL REFERENCES users(username),
  role varchar(45) NOT NULL,
  UNIQUE (role,username)
);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');

CREATE  TABLE connections (
  id SERIAL PRIMARY KEY,
  db_name VARCHAR (45),
  db_user_name VARCHAR (45)
);

CREATE  TABLE user_actions (
  id SERIAL PRIMARY KEY,
  date TIMESTAMP NOT NULL,
  user_username VARCHAR (45) NOT NULL REFERENCES users(username) ,
  connection_id INTEGER REFERENCES connections(id) ,
  action VARCHAR (200)
);
