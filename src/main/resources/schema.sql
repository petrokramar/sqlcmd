-- Database sqlcmd_log
DROP TABLE IF EXISTS user_actions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS connections;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL PRIMARY KEY,
  password VARCHAR(60) NOT NULL ,
  email VARCHAR(45) NOT NULL ,
  enabled BOOLEAN NOT NULL DEFAULT 'true'
);
-- Password 1234
INSERT INTO users(username,password, email)
VALUES ('admin','$2a$10$vu70icFlcjTqw7ch3/ndpu35nSZNSuzIDO.T5XKaKL7AWAKhusYr2', 'admin@zzz.com');
INSERT INTO users(username,password, email)
VALUES ('user','$2a$10$vu70icFlcjTqw7ch3/ndpu35nSZNSuzIDO.T5XKaKL7AWAKhusYr2', 'user@zzz.com');

CREATE TABLE user_roles (
  user_role_id SERIAL PRIMARY KEY,
  username VARCHAR(45) NOT NULL REFERENCES users(username),
  role VARCHAR(45) NOT NULL,
  UNIQUE (role,username)
);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');

CREATE  TABLE connections (
  id SERIAL PRIMARY KEY,
  server VARCHAR (45),
  port VARCHAR (5),
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