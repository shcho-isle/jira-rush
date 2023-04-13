DELETE FROM profile;
DELETE FROM user_role;
DELETE FROM users;
ALTER TABLE users ALTER COLUMN id RESTART WITH 1;

insert into users (EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, DISPLAY_NAME, STARTPOINT)
values ('user@yandex.ru', '{noop}password', 'userFirstName', 'userLastName', 'userDisplayName', now()),
       ('admin@gmail.com', '{noop}admin', 'adminFirstName', 'adminLastName', 'adminDisplayName', now()),
       ('guest@gmail.com', '{noop}guest', 'guestFirstName', 'guestLastName', 'guestDisplayName', now());

-- 0 DEV
-- 1 ADMIN
insert into USER_ROLE (ROLE, USER_ID)
values (0, 1),
       (1, 2),
       (0, 2);

DELETE FROM reference;
ALTER TABLE reference ALTER COLUMN id RESTART WITH 1;
--============ References =================
insert into reference (CODE, TITLE, REF_TYPE, STARTPOINT)
-- TASK
values ('task', 'Task', 2, now()),
       ('story', 'Story', 2, now()),
       ('bug', 'Bug', 2, now()),
       ('epic', 'Epic', 2, now()),
-- TASK_STATUS
       ('icebox', 'Icebox', 3, now()),
       ('backlog', 'Backlog', 3, now()),
       ('ready', 'Ready', 3, now()),
       ('in progress', 'In progress', 3, now()),
       ('done', 'Done', 3, now()),
-- SPRINT_STATUS
       ('planning', 'Planning', 4, now()),
       ('implementation', 'Implementation', 4, now()),
       ('review', 'Review', 4, now()),
       ('retrospective', 'Retrospective', 4, now()),
-- USER_TYPE
       ('admin', 'Admin', 5, now()),
       ('user', 'User', 5, now()),
-- PROJECT
       ('scrum', 'Scrum', 1, now()),
       ('task tracker', 'Task tracker', 1, now()),
-- CONTACT
       ('skype', 'Skype', 0, now()),
       ('tg', 'Telegram', 0, now()),
       ('mobile', 'Mobile', 0, now()),
       ('phone', 'Phone', 0, now()),
       ('website', 'Website', 0, now()),
       ('vk', 'VK', 0, now()),
       ('linkedin', 'LinkedIn', 0, now()),
       ('github', 'GitHub', 0, now()),
-- PRIORITY
       ('critical', 'Critical', 7, now()),
       ('high', 'High', 7, now()),
       ('normal', 'Normal', 7, now()),
       ('low', 'Low', 7, now()),
       ('neutral', 'Neutral', 7, now());

insert into reference (CODE, TITLE, REF_TYPE, AUX, STARTPOINT)
-- MAIL_NOTIFICATION
values ('assigned', 'Assigned', 6, '1', now()),
       ('three_days_before_deadline', 'Three days before deadline', 6, '2', now()),
       ('two_days_before_deadline', 'Two days before deadline', 6, '4', now()),
       ('one_day_before_deadline', 'One day before deadline', 6, '8', now()),
       ('deadline', 'Deadline', 6, '16', now()),
       ('overdue', 'Overdue', 6, '32', now());

insert into profile (ID, LAST_FAILED_LOGIN, LAST_LOGIN, MAIL_NOTIFICATIONS)
values (1, null, null, 49),
       (2, null, null, 14);

DELETE FROM contact;
insert into contact (ID, CODE, CONTACT_VALUE)
values (1, 'skype', 'userSkype'),
       (1, 'mobile', '+71234567890'),
       (1, 'website', 'user.com'),
       (2, 'github', 'adminGitHub'),
       (2, 'tg', 'adminTg'),
       (2, 'vk', 'adminVk');
