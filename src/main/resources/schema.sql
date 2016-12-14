-- Database sqlcmd_log
DROP TABLE IF EXISTS user_actions;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS connections;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL PRIMARY KEY,
  password VARCHAR(45) NOT NULL ,
  email VARCHAR(45) NOT NULL ,
  enabled INTEGER NOT NULL DEFAULT 1
);

-- CREATE TABLE public.users
-- (
--     username character varying(45) COLLATE pg_catalog."default" NOT NULL,
--     password character varying(45) COLLATE pg_catalog."default" NOT NULL,
--     email character varying(45) COLLATE pg_catalog."default" NOT NULL,
--     enabled integer NOT NULL DEFAULT 1,
--     CONSTRAINT users_pkey PRIMARY KEY (username)
-- )
-- WITH (
--     OIDS = FALSE
-- )
-- TABLESPACE pg_default;
--
-- ALTER TABLE public.users
--     OWNER to postgres;

INSERT INTO users(username,password, email)
VALUES ('admin','1234', 'admin@zzz.com');
INSERT INTO users(username,password, email)
VALUES ('user','1234', 'user@zzz.com');

CREATE TABLE user_roles (
  user_role_id SERIAL PRIMARY KEY,
  username varchar(45) NOT NULL REFERENCES users(username),
  role varchar(45) NOT NULL,
  UNIQUE (role,username)
);

-- CREATE TABLE public.user_roles
-- (
--     user_role_id integer NOT NULL DEFAULT nextval('user_roles_user_role_id_seq'::regclass),
--     username character varying(45) COLLATE pg_catalog."default" NOT NULL,
--     role character varying(45) COLLATE pg_catalog."default" NOT NULL,
--     CONSTRAINT user_roles_pkey PRIMARY KEY (user_role_id),
--     CONSTRAINT user_roles_role_username_key UNIQUE (role, username),
--     CONSTRAINT user_roles_username_fkey FOREIGN KEY (username)
--         REFERENCES public.users (username) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION
-- )
-- WITH (
--     OIDS = FALSE
-- )
-- TABLESPACE pg_default;
--
-- ALTER TABLE public.user_roles
--     OWNER to postgres;

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

-- CREATE TABLE public.connections
-- (
--     id integer NOT NULL DEFAULT nextval('connections_id_seq'::regclass),
--     db_name character varying(45) COLLATE pg_catalog."default",
--     db_user_name character varying(45) COLLATE pg_catalog."default",
--     CONSTRAINT connections_pkey PRIMARY KEY (id)
-- )
-- WITH (
--     OIDS = FALSE
-- )
-- TABLESPACE pg_default;
--
-- ALTER TABLE public.connections
--     OWNER to postgres;

CREATE  TABLE user_actions (
  id SERIAL PRIMARY KEY,
  date TIMESTAMP NOT NULL,
  user_username VARCHAR (45) NOT NULL REFERENCES users(username) ,
  connection_id INTEGER REFERENCES connections(id) ,
  action VARCHAR (200)
);

-- CREATE TABLE public.user_actions
-- (
--     id integer NOT NULL DEFAULT nextval('user_actions_id_seq'::regclass),
--     date timestamp without time zone NOT NULL,
--     user_username character varying(45) COLLATE pg_catalog."default" NOT NULL,
--     connection_id integer,
--     action character varying(200) COLLATE pg_catalog."default",
--     CONSTRAINT user_actions_pkey PRIMARY KEY (id),
--     CONSTRAINT user_actions_connection_id_fkey FOREIGN KEY (connection_id)
--         REFERENCES public.connections (id) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION,
--     CONSTRAINT user_actions_user_username_fkey FOREIGN KEY (user_username)
--         REFERENCES public.users (username) MATCH SIMPLE
--         ON UPDATE NO ACTION
--         ON DELETE NO ACTION
-- )
-- WITH (
--     OIDS = FALSE
-- )
-- TABLESPACE pg_default;
--
-- ALTER TABLE public.user_actions
--     OWNER to postgres;