CREATE TABLE IF NOT EXISTS caller (
id BIGSERIAL PRIMARY KEY,
username VARCHAR UNIQUE NOT NULL,
password VARCHAR NOT NULL,
email VARCHAR UNIQUE NOT NULL,
message VARCHAR NOT NULL DEFAULT 'Hey!\nThis message is forwarded from Emergency Notification System. Please call me back.',
image VARCHAR
);

CREATE TABLE IF NOT EXISTS caller2receiver_binding (
id BIGSERIAL PRIMARY KEY,
caller_id BIGINT NOT NULL REFERENCES caller(id) ON DELETE CASCADE ON UPDATE CASCADE,
receiver VARCHAR NOT NULL,
service VARCHAR NOT NULL,
service_link VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS emergency_calls (
id BIGSERIAL PRIMARY KEY,
binding_id BIGINT NOT NULL REFERENCES caller2receiver_binding(id) ON DELETE NO ACTION ON UPDATE CASCADE,
date_called TIMESTAMP NOT NULL,
date_received TIMESTAMP
);

