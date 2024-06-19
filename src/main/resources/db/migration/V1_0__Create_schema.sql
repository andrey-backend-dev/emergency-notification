CREATE SEQUENCE caller_id_seq MINVALUE 5 INCREMENT 5;

CREATE TABLE IF NOT EXISTS caller (
id BIGINT PRIMARY KEY default nextval('caller_id_seq'),
username VARCHAR UNIQUE NOT NULL,
password VARCHAR NOT NULL,
email VARCHAR UNIQUE NOT NULL,
message VARCHAR NOT NULL DEFAULT 'Hey!\nThis message is forwarded from Emergency Notification System. Please call me back.',
image VARCHAR
);

CREATE SEQUENCE binding_id_seq MINVALUE 14 INCREMENT 5;

CREATE TABLE IF NOT EXISTS caller2receiver_binding (
id BIGINT PRIMARY KEY default nextval('binding_id_seq'),
caller_id BIGINT NOT NULL REFERENCES caller(id) ON DELETE CASCADE ON UPDATE CASCADE,
receiver VARCHAR NOT NULL,
service VARCHAR NOT NULL,
service_link VARCHAR NOT NULL
);

CREATE SEQUENCE emergency_id_seq MINVALUE 7 INCREMENT 5;

CREATE TABLE IF NOT EXISTS emergency_calls (
id BIGINT PRIMARY KEY default nextval('emergency_id_seq'),
binding_id BIGINT NOT NULL REFERENCES caller2receiver_binding(id) ON DELETE NO ACTION ON UPDATE CASCADE,
date_called TIMESTAMP NOT NULL,
date_received TIMESTAMP
);