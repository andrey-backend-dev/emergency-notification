INSERT INTO caller (id, username, password, email) VALUES
(1, 'ekynd0', 'simplepass', 'tenders0@geocities.jp'),
(2, 'nwisher1', 'simplepass', 'gtomich1@ow.ly'),
(3, 'tcrookes2', 'simplepass', 'hlucks2@theguardian.com'),
(4, 'blethlay3', 'simplepass', 'bmaxstead3@a8.net'),
(5, 'bdavidwitz4', 'simplepass', 'wtayt4@edublogs.org');

INSERT INTO caller2receiver_binding (id, caller_id, receiver, service, service_link) VALUES
(1, 1, 'Andrey Semenov', 'EMAIL', 'kalibrovkaaccount@mail.ru'),
(2, 2, 'Andrey Semenov', 'TELEGRAM', 'https://t.me/scythejudge'),
(3, 4, 'Christoper Rubica', 'EMAIL', 'emacey1@icio.us'),
(4, 2, 'Judi Acuna', 'EMAIL', 'adabell2@hp.com'),
(5, 2, 'Catha Philpotts', 'TELEGRAM', 'https://t.me/emacey1'),
(6, 3, 'Novelia Duchasteau', 'TELEGRAM', 'https://t.me/scythejudge'),
(7, 5, 'Cariotta Lovejoy', 'EMAIL', 'sbackshaw3@intel.com'),
(8, 3, 'Thatcher Yele', 'TELEGRAM', 'https://t.me/boades4'),
(9, 1, 'Charlotte Joye', 'EMAIL', 'boades4@nsw.gov.au'),
(10, 5, 'Ingemar Treneer', 'TELEGRAM', 'https://t.me/r36ewgnz'),
(11, 3, 'Zeke Fenton', 'TELEGRAM', 'https://t.me/kkweebql'),
(12, 2, 'Lyda Jachimczak', 'TELEGRAM', 'https://t.me/r36ewgnz'),
(13, 1, 'Darline Spracklin', 'TELEGRAM', 'https://t.me/kkweebql'),
(14, 5, 'Andrey Semenov', 'TELEGRAM', 'https://t.me/scythejudge');

INSERT INTO emergency_calls (id, binding_id, date_called, date_received) VALUES
(1, 1, '2024-02-29 13:05:51', null),
(2, 3, '2023-12-15 04:50:00', '2023-12-15 04:50:01'),
(3, 4, '2023-05-18 07:48:00', '2023-5-18 07:48:04'),
(4, 5, '2023-09-09 14:50:09', '2023-9-9 14:50:12'),
(5, 2, '2023-11-09 23:22:38', '2023-11-9 23:22:42'),
(6, 2, '2023-08-07 20:04:26', '2023-8-7 20:04:30'),
(7, 2, '2024-01-21 08:06:19', null);

select nextval('caller_id_seq');
select nextval('binding_id_seq');
select nextval('emergency_id_seq');