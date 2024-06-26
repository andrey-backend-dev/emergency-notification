INSERT INTO role (name) VALUES
('admin'),
('user');

INSERT INTO caller (id, username, password, email) VALUES
(1, 'bladeattheneck', 'simplepass', 'kalibrovkaaccount@mail.ru');

INSERT INTO caller2role (id, caller_id, role_name) VALUES
(1, 1, 'admin');

INSERT INTO receiver (id, caller_id, name, email, telegram_id) VALUES
(1, 1, 'Andrey', 'vesselofgarbage@gmail.com', null);

select nextval('caller2role_id_seq');
select nextval('caller_id_seq');
select nextval('receiver_id_seq');
