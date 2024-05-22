CREATE DATABASE emergency_notifications_db;

\c emergency_notifications_db;

CREATE TABLE caller (
id BIGSERIAL PRIMARY KEY,
username VARCHAR UNIQUE NOT NULL,
password VARCHAR NOT NULL,
email VARCHAR UNIQUE NOT NULL,
message VARCHAR NOT NULL DEFAULT 'Hey!\nThis message is forwarded from Emergency Notification System. Please call me back.',
image VARCHAR
);

CREATE TABLE caller2receiver_binding (
id BIGSERIAL PRIMARY KEY,
caller_id BIGINT NOT NULL REFERENCES caller(id),
receiver VARCHAR NOT NULL,
service VARCHAR NOT NULL,
service_link VARCHAR NOT NULL
);

CREATE TABLE emergency_calls (
id BIGSERIAL PRIMARY KEY,
binding_id BIGINT NOT NULL REFERENCES caller2receiver_binding(id),
date_called TIMESTAMP NOT NULL,
date_received TIMESTAMP
);

